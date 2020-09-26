package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocalStorage
import ir.aliiz.data.repo.LocationRepo

class RemoteLocationRepo(
    private val localStorage: LocalStorage<LatLng>
) : LocationRepo {
    override fun getCity(location: LatLng): Observable<String> = Observable.just("tehran")
    override fun updateLocation(location: LatLng): Completable = Completable.defer {
        localStorage.save(location)
        return@defer Completable.complete()
    }

    override fun getLocation(): Observable<LatLng> = localStorage.data
}