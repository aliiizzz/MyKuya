package ir.aliiz.data

import ir.aliiz.data.remote.RemoteLocationRepo
import ir.aliiz.data.remote.RemoteNewsRepo
import ir.aliiz.data.remote.RemoteServiceRepo
import ir.aliiz.data.remote.RemoteUserRepo
import ir.aliiz.data.repo.LocationRepo
import ir.aliiz.data.repo.NewsRepo
import ir.aliiz.data.repo.ServiceRepo
import ir.aliiz.data.repo.UserRepo
import org.koin.dsl.module

val dataModule = module {
    single<NewsRepo> { RemoteNewsRepo(get()) }
    single<ServiceRepo> { RemoteServiceRepo(get()) }
    single<UserRepo> { RemoteUserRepo(get()) }
    single<LocationRepo> { RemoteLocationRepo() }
}