package ir.aliiz.service

import org.koin.dsl.module

val serviceModule = module {
    factory { ServicePresenter(get(), get(), get(), get(), get(), get()) }
    single<ServiceNotifier> { PublisherServiceNotifier() }
}