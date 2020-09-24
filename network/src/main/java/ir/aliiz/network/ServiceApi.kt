package ir.aliiz.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi  {
    @GET("services/promotion")
    fun getPromotions(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double): Single<PromotionResponse>

    @GET("services")
    fun getAllServices(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double): Single<ServicesResponse>

}