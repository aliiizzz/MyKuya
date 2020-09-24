package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Observable

interface NewsRepo {
    fun getNews(): Observable<List<News>>
}