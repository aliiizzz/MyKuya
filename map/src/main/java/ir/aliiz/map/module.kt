package ir.aliiz.map

import org.koin.dsl.module

val mapModule = module {
    factory { MapPresenter(get(), get()) }
}