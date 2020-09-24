package ir.aliiz.network.fake

import io.reactivex.rxjava3.core.Single
import ir.aliiz.network.Profile
import ir.aliiz.network.ProfileApi
import ir.aliiz.network.ProfileResponse
import java.util.concurrent.TimeUnit

class FakeProfileApi : ProfileApi {
    override fun getProfile(): Single<ProfileResponse> = Single.just(
        ProfileResponse(
        Profile("1", "somebody", "anybody")
    )
    ).delay(1000, TimeUnit.MILLISECONDS)
}