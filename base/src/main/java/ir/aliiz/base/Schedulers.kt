package ir.aliiz.base

import io.reactivex.rxjava3.core.Scheduler

data class Schedulers(
    val main: Scheduler,
    val io: Scheduler
)