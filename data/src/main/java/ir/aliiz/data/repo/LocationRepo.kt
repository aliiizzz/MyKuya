package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Observable

interface LocationRepo {
    fun getCity(location: LatLng): Observable<String>
}