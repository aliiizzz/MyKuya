package ir.aliiz.map

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.aliiz.base.BasePresenter
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocationRepo

class MapPresenter(
    private val locationRepo: LocationRepo,
    private val schedulers: Schedulers
) : BasePresenter<MapPresenter.MapView>() {

    private val locationUpdate = PublishSubject.create<LatLng>()
    override fun viewAttached() {
        locationUpdate.flatMap {
            locationRepo.updateLocation(it).subscribeOn(schedulers.io).andThen(Observable.just(Unit))
        }.observeOn(schedulers.main).subscribe({
            view()?.locationUpdated()
        }, {
            view()?.locationUpdateError(it)
        }).add()
    }

    fun submitLocation(location: LatLng) {
        locationUpdate.onNext(location)
    }

    interface MapView {
        fun locationUpdated()
        fun locationUpdateError(it: Throwable?)
    }
}