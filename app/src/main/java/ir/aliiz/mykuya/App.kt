package ir.aliiz.mykuya

import android.app.Application
import ir.aliiz.data.dataModule
import ir.aliiz.network.networkModule
import ir.aliiz.service.serviceModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                mainModule,
                serviceModule,
                networkModule,
                dataModule
            ))
        }
    }
}