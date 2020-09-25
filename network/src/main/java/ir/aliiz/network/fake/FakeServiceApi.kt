package ir.aliiz.network.fake

import io.reactivex.rxjava3.core.Single
import ir.aliiz.network.PromotionResponse
import ir.aliiz.network.Service
import ir.aliiz.network.ServiceApi
import ir.aliiz.network.ServicesResponse
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class FakeServiceApi : ServiceApi {
    override fun getPromotions(latitude: Double, longitude: Double): Single<PromotionResponse> =
        Single.just(
            PromotionResponse(
            (0 until 3).map { Service("test", "icon${Random.nextInt(2, 28)}") }
        )
        ).delay(1000, TimeUnit.MILLISECONDS)

    override fun getAllServices(latitude: Double, longitude: Double): Single<ServicesResponse> =
        Single.just(
            ServicesResponse(
                (0 until 20).map { Service("service 1", "something") }
            )
        ).delay(2000, TimeUnit.MILLISECONDS)
}