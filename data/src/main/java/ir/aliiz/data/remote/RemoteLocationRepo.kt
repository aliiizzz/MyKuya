package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocationRepo

class RemoteLocationRepo : LocationRepo {
    override fun getCity(location: LatLng): Observable<String> = Observable.just("tehran")
}