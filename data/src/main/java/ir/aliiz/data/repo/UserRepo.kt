package ir.aliiz.data.repo

import io.reactivex.rxjava3.core.Observable

interface UserRepo {
    fun getProfile(): Observable<User>
}