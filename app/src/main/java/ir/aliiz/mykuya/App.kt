package ir.aliiz.mykuya

import android.app.Application
import ir.aliiz.data.dataModule
import ir.aliiz.map.mapModule
import ir.aliiz.network.networkModule
import ir.aliiz.service.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                mainModule,
                serviceModule,
                networkModule,
                dataModule,
                mapModule
            ))
        }
    }
}