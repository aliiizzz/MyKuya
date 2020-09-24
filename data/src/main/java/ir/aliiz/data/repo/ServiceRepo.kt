package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Observable

interface ServiceRepo {
    fun getFeaturedServices(location: LatLng): Observable<List<Service>>
    fun getAllServices(location: LatLng): Observable<List<Service>>
}