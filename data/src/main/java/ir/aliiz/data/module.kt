package ir.aliiz.data

import ir.aliiz.data.remote.*
import ir.aliiz.data.repo.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<NewsRepo> { RemoteNewsRepo(get()) }
    single<ServiceRepo> { RemoteServiceRepo(get()) }
    single<UserRepo> { RemoteUserRepo(get()) }
    single<LocationRepo> { RemoteLocationRepo(get()) }
    single<LocalStorage<LatLng>> { PrefLocationStorage(androidContext()) }
}