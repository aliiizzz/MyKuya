package ir.aliiz.data.remote

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocalStorage

class PrefLocationStorage(val context: Context) : LocalStorage<LatLng> {
    private var pref: SharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE)
    private val subject = BehaviorSubject.create<LatLng>()

    init {
        val lat = pref.getString("latitude", null)
        val lng = pref.getString("longitude", null)
        if (lat != null && lng != null) {
            subject.onNext(LatLng(lat.toDouble(), lng.toDouble()))
        }
    }
    override fun save(data: LatLng) {
        pref.edit().apply {
            putString("latitude", data.latitude.toString())
            putString("longitude", data.longitude.toString())
            apply()
        }
        subject.onNext(data)
    }

    override val data: Observable<LatLng>
        get() = subject
}