package ir.aliiz.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

data class Schedulers(
    val main: Scheduler = Schedulers.io(),
    val io: Scheduler = AndroidSchedulers.mainThread()
)