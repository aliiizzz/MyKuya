package ir.aliiz.network.fake

import io.reactivex.rxjava3.core.Single
import ir.aliiz.network.News
import ir.aliiz.network.NewsApi
import ir.aliiz.network.NewsResponse
import java.util.concurrent.TimeUnit

class FakeNewsApi : NewsApi {
    override fun getNews(): Single<NewsResponse> = Single.just(
        NewsResponse(
        listOf(News("1", "some news", "news description", "banner"))
    )
    ).delay(2000, TimeUnit.MILLISECONDS)
}