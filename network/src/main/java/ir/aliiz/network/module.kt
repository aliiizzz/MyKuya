package ir.aliiz.network

import ir.aliiz.network.fake.FakeNewsApi
import ir.aliiz.network.fake.FakeProfileApi
import ir.aliiz.network.fake.FakeServiceApi
import org.koin.dsl.module

val networkModule = module {
    single<NewsApi> { FakeNewsApi() }
    single<ProfileApi> { FakeProfileApi() }
    single<ServiceApi> { FakeServiceApi() }
}