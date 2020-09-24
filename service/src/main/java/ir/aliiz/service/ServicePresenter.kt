package ir.aliiz.service

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.aliiz.base.BasePresenter
import ir.aliiz.base.Loadable
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.*

class ServicePresenter(
    private val serviceRepo: ServiceRepo,
    private val newsRepo: NewsRepo,
    private val userRepo: UserRepo,
    private val locationRepo: LocationRepo,
    private val schedulers: Schedulers
): BasePresenter<ServicePresenter.ServiceView>() {

    private val locationPublisher: PublishSubject<LatLng> = PublishSubject.create()
    private val refreshPublisher: PublishSubject<Unit> = PublishSubject.create()

    fun init() {

        val refreshShare = refreshPublisher.share()
        val refreshLocation = Observable.combineLatest(refreshShare, locationPublisher,
            BiFunction<Unit, LatLng, LatLng> { t1, t2 ->
                t2
            }).share()
        refreshLocation.flatMap {
            serviceRepo.getFeaturedServices(it)
                .subscribeOn(schedulers.io)
        }.subscribe({
            view()?.promotedServices(Loadable.Loaded(it))
        }, {
            view()?.promotedServices(Loadable.Failed(it))
        }).add()

        refreshLocation.flatMap {
            serviceRepo.getAllServices(it).subscribeOn(schedulers.io)
        }.subscribe ({
            view()?.allServices(Loadable.Loaded(it))
        }, {
            view()?.allServices(Loadable.Failed(it))
        }).add()

        refreshShare.flatMap {
            newsRepo.getNews().subscribeOn(schedulers.io)
        }.subscribe({
            view()?.updateNews(Loadable.Loaded(it))
        }, {
            view()?.updateNews(Loadable.Failed(it))
        }).add()
        userRepo.getProfile().subscribeOn(schedulers.io).subscribe({
            view()?.updateProfile(Loadable.Loaded(it))
        }, {
            view()?.updateProfile(Loadable.Failed(it))
        }).add()
        refreshLocation.flatMap {
            locationRepo.getCity(it)
        }.subscribeOn(schedulers.io).subscribe({
            view()?.updateCity(it)
        }, {
            it.printStackTrace()
        }).add()

        refreshPublisher.onNext(Unit)
    }

    fun updateLocation(location: LatLng) {
        locationPublisher.onNext(location)
    }

    fun refreshNews() {
        refreshPublisher.onNext(Unit)
    }

    interface ServiceView {
        fun promotedServices(data: Loadable<List<Service>>)
        fun allServices(data: Loadable<List<Service>>)
        fun updateNews(data: Loadable<List<News>>)
        fun updateProfile(data: Loadable<User>)
        fun updateCity(name: String)
    }
}