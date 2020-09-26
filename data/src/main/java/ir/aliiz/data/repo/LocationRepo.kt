package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface LocationRepo {
    fun getCity(location: LatLng): Observable<String>
    fun updateLocation(location: LatLng): Completable
    fun getLocation(): Observable<LatLng>
}