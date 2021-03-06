package ir.aliiz.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.aliiz.base.Loadable
import ir.aliiz.base.onFail
import ir.aliiz.base.onLoad
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.News
import ir.aliiz.data.repo.Service
import ir.aliiz.data.repo.User
import ir.aliiz.navigation.LocationNto
import kotlinx.android.synthetic.main.fragment_service.*
import org.koin.android.ext.android.inject

class ServiceFragment : Fragment(), ServicePresenter.ServiceView {

    private val presenter: ServicePresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_service, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this, viewLifecycleOwner.lifecycle)
        val featuredAdapter = ServiceAdapter()
        val allServicesAdapter = ServiceAdapter().apply {
            isExpanded = false
            updateImage(isExpanded)
        }
        imageServiceExpanded.setOnClickListener {
            recyclerServiceAll.adapter().apply {
                isExpanded = !isExpanded
                notifyDataSetChanged()
                updateImage(isExpanded)
            }
        }
        recyclerServiceAll.adapter = allServicesAdapter
        recyclerServiceFeatureCardFeatures.adapter = featuredAdapter
        recyclerServiceNews.adapter = NewsAdapter()
        textServiceLocation.setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(context!!, R.drawable.ic_location_pin_with_hole_and_shadow),
            null, null, null
        )

        textServiceLocation.setOnClickListener {
            presenter.requestMap()
        }
    }

    private fun updateImage(expanded: Boolean) {
        imageServiceExpanded.rotationX = if (expanded) 0f else 180f
    }

    override fun promotedServices(data: Loadable<List<Service>>) {
        data.onLoad {
            recyclerServiceFeatureCardFeatures.adapter().apply {
                items = this@onLoad
                notifyDataSetChanged()
            }
        }

        data.onFail {

        }
    }

    override fun allServices(data: Loadable<List<Service>>) {
        data.onLoad {
            recyclerServiceAll.adapter().apply {
                items = this@onLoad
                notifyDataSetChanged()
            }
        }
        data.onFail {

        }
    }

    override fun updateNews(data: Loadable<List<News>>) {
        data.onLoad {
            (recyclerServiceNews.adapter as NewsAdapter).apply {
                items = this@onLoad
                notifyDataSetChanged()
            }
        }
        data.onFail {

        }
    }

    override fun updateProfile(data: Loadable<User>) {
        data.onLoad {
            textServiceWelcome.text = getString(R.string.welcome_name, name)
        }
        data.onFail {

        }
    }

    override fun updateCity(name: String) {
        textServiceLocation.text = name
    }

    override fun loadMap(location: LatLng) {
        findNavController().navigate(ServiceFragmentDirections.actionServiceToMap(
            location.toLocationNto()
        ))
    }

    private fun RecyclerView.adapter() = (adapter as ServiceAdapter)
}


fun LatLng.toLocationNto() = LocationNto(latitude, longitude)
