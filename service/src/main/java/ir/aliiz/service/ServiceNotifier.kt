package ir.aliiz.service

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.LatLng

interface ServiceNotifier {
    fun selectService(service: Service)
    val services: Observable<Service>

    sealed class Service {
        data class Map(val location: LatLng): Service()
    }
}