package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.Service
import ir.aliiz.data.repo.ServiceRepo
import ir.aliiz.data.toPromotions
import ir.aliiz.data.toServices
import ir.aliiz.network.ServiceApi

class RemoteServiceRepo(
    private val serviceApi: ServiceApi
) : ServiceRepo {
    override fun getFeaturedServices(location: LatLng): Observable<List<Service>> =
        serviceApi.getPromotions(location.latitude, location.longitude).toObservable().toPromotions()

    override fun getAllServices(location: LatLng): Observable<List<Service>> =
        serviceApi.getAllServices(location.latitude, location.longitude).toObservable().toServices()
}