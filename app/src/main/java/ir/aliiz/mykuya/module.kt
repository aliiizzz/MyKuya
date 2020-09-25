package ir.aliiz.mykuya
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ir.aliiz.base.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers as rxscheduler
import org.koin.dsl.module

val mainModule = module {
    single { Schedulers(AndroidSchedulers.mainThread(), rxscheduler.io()) }
}