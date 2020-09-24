package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.News
import ir.aliiz.data.repo.NewsRepo
import ir.aliiz.data.toNews
import ir.aliiz.network.NewsApi

class RemoteNewsRepo(
    private val newsApi: NewsApi
) : NewsRepo {
    override fun getNews(): Observable<List<News>> = newsApi.getNews().toObservable().toNews()
}