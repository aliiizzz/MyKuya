package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.User
import ir.aliiz.data.repo.UserRepo
import ir.aliiz.data.toUser
import ir.aliiz.network.ProfileApi

class RemoteUserRepo(
    private val profileApi: ProfileApi
) : UserRepo {
    override fun getProfile(): Observable<User> = profileApi.getProfile().toObservable().toUser()
}