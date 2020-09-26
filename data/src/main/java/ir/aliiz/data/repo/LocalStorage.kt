package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Observable

interface LocalStorage<T> {
    fun save(data: T)
    val data: Observable<T>
}