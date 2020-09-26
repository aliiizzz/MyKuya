package ir.aliiz.map

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import ir.aliiz.navigation.LocationNto
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.ext.android.inject

class MapFragment : Fragment(), MapPresenter.MapView {
    private var map: GoogleMap? = null
    private val args: MapFragmentArgs by navArgs()

    private val mapPresenter: MapPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_map, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapPresenter.attachView(this, viewLifecycleOwner.lifecycle)
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
        imageMapPin.post {
            imageMapPin.also {
                it.translationY -= it.height / 2
            }
        }

        buttonMapSubmit.setOnClickListener {
            map?.cameraPosition?.target?.let {
                mapPresenter.submitLocation(
                    ir.aliiz.data.repo.LatLng(it.latitude, it.longitude)
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            val fineLocationIndex = permissions.indexOfFirst { it == Manifest.permission.ACCESS_FINE_LOCATION }
            loadMap(grantResults[fineLocationIndex] == PermissionChecker.PERMISSION_GRANTED)
        }
    }

    private fun loadMap(showMyLocation: Boolean) {
        (childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment).getMapAsync {
            map = it
            it.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    args.location.toLatLng(), 13f
                )
            )
            it.uiSettings.apply {
                if (showMyLocation) {
                    this.isMyLocationButtonEnabled = true
                }
            }
            if (showMyLocation) {
                it.isMyLocationEnabled = true
            }
        }

    }

    override fun locationUpdated() {
        findNavController().popBackStack()
    }

    override fun locationUpdateError(it: Throwable?) {
        Toast.makeText(context!!, getString(R.string.error_happened), Toast.LENGTH_LONG).show()
    }

    private fun LocationNto.toLatLng() = LatLng(latitude, longitude)
}