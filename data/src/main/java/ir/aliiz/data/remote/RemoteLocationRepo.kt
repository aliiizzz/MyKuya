package ir.aliiz.data.remote

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocalStorage
import ir.aliiz.data.repo.LocationRepo
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class RemoteLocationRepo(
    private val localStorage: LocalStorage<LatLng>
) : LocationRepo {
    private val locations = listOf(
        Pair(
            LatLng(35.690343, 51.308863), "Mehrabad"
        ),
        Pair(
            LatLng(35.791806, 51.426000), "Tajrish"
        ),
        Pair(
            LatLng(35.738239, 51.529659), "Tehranpars"
        ),
        Pair(
            LatLng(35.696855, 51.378640), "Jomhori"
        ),
        Pair(
            LatLng(35.778893, 51.372155), "Saadat Abad"
        )
    )
    override fun getCity(location: LatLng): Observable<String> = Observable.defer {
        val minDistance = locations.minBy { it.first.distance(location) }
        return@defer Observable.just(minDistance?.second)
    }
    override fun updateLocation(location: LatLng): Completable = Completable.defer {
        localStorage.save(location)
        return@defer Completable.complete()
    }

    override fun getLocation(): Observable<LatLng> = localStorage.data

    /*
    copy from {@link https://stackoverflow.com/questions/6981916/how-to-calculate-distance-between-two-locations-using-their-longitude-and-latitu }
    Measure distance between two Coordinates
     */
    private fun LatLng.distance(
        location: LatLng
    ): Double {
        val theta = longitude - location.longitude
        var dist = (Math.sin(deg2rad(latitude))
                * Math.sin(deg2rad(location.latitude))
                + (Math.cos(deg2rad(latitude))
                * Math.cos(deg2rad(location.latitude))
                * Math.cos(deg2rad(theta))))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}