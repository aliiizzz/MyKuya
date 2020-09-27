package ir.aliiz.service

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class PublisherServiceNotifier : ServiceNotifier {
    private val publisher = PublishSubject.create<ServiceNotifier.Service>()

    override fun selectService(service: ServiceNotifier.Service) {
        publisher.onNext(service)
    }

    override val services: Observable<ServiceNotifier.Service>
        get() = publisher
}