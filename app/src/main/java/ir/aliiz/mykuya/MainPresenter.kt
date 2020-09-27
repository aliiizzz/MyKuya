package ir.aliiz.mykuya

import ir.aliiz.base.BasePresenter
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocationRepo
import ir.aliiz.service.ServiceNotifier

class MainPresenter(
    private val locationRepo: LocationRepo,
    private val serviceNotifier: ServiceNotifier,
    private val schedulers: Schedulers
) : BasePresenter<MainPresenter.MainView>() {

    override fun viewAttached() {
        locationRepo.getLocation().subscribeOn(schedulers.io)
            .observeOn(schedulers.main).subscribe({
            if (it == LatLng(1.0, 1.0)) {
                view()?.loadMapScreen()
            }
        }, {

        }).add()

        serviceNotifier.services.subscribeOn(schedulers.io)
            .observeOn(schedulers.main).subscribe {
                when(it) {
                    is ServiceNotifier.Service.Map -> view()?.loadMap(it.location)
                }
            }.add()
    }

    interface MainView {
        fun loadMapScreen()
        fun loadMap(location: LatLng)
    }
}