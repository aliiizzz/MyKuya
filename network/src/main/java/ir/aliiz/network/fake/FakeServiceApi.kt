package ir.aliiz.network.fake

import io.reactivex.rxjava3.core.Single
import ir.aliiz.network.PromotionResponse
import ir.aliiz.network.Service
import ir.aliiz.network.ServiceApi
import ir.aliiz.network.ServicesResponse
import java.util.concurrent.TimeUnit

class FakeServiceApi : ServiceApi {
    override fun getPromotions(latitude: Double, longitude: Double): Single<PromotionResponse> =
        Single.just(
            PromotionResponse(
            listOf(
                Service("service 1", "something")
            )
        )
        ).delay(1000, TimeUnit.MILLISECONDS)

    override fun getAllServices(latitude: Double, longitude: Double): Single<ServicesResponse> =
        Single.just(
            ServicesResponse(
                listOf(
                    Service("service 1", "something")
                )
            )
        ).delay(2000, TimeUnit.MILLISECONDS)
}