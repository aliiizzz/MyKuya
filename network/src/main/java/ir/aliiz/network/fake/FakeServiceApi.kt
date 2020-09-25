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
                Service("Massage", "icon25"),
                Service("Cleaning", "icon2"),
                Service("Shopping", "icon8")
            )
        )
        ).delay(1000, TimeUnit.MILLISECONDS)

    override fun getAllServices(latitude: Double, longitude: Double): Single<ServicesResponse> =
        Single.just(
            ServicesResponse(
                listOf(
                    Service("Cleaning", "icon2"),
                    Service("Event Assistant", "icon3"),
                    Service("Office Assistant", "icon4"),
                    Service("Coffee Delivery", "icon6"),
                    Service("Food Delivery", "icon7"),
                    Service("Shopping", "icon8"),
                    Service("Grocery Delivery", "icon9"),
                    Service("Messenger", "icon10"),
                    Service("Bills Payment", "icon12"),
                    Service("Personal Assistant", "icon13"),
                    Service("Assistant on Bike", "icon14"),
                    Service("Queueing Up", "icon15"),
                    Service("Pet Sitting", "icon16"),
                    Service("Flying", "icon17"),
                    Service("Dish Washing", "icon23"),
                    Service("Cash on Delivery", "icon24"),
                    Service("Massage", "icon25"),
                    Service("Deep Clean", "icon26"),
                    Service("Car Wash", "icon27"),
                    Service("Manicure", "icon28")
                )
            )
        ).delay(2000, TimeUnit.MILLISECONDS)
}