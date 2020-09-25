package ir.aliiz.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.aliiz.base.Loadable
import ir.aliiz.base.onFail
import ir.aliiz.base.onLoad
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.News
import ir.aliiz.data.repo.Service
import ir.aliiz.data.repo.User
import kotlinx.android.synthetic.main.fragment_service.*
import org.koin.android.ext.android.inject
import kotlin.math.ceil
import kotlin.math.min

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
        toolbarService.title = "test"
        presenter.attachView(this, viewLifecycleOwner.lifecycle)
        val featuredAdapter = ServiceAdapter()
        val allServicesAdapter = ServiceAdapter()
        recyclerServiceAll.adapter = allServicesAdapter
        recyclerServiceFeatureCardFeatures.adapter = featuredAdapter
        presenter.updateLocation(LatLng(1.0, 1.0))
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

    private fun RecyclerView.adapter() = (adapter as ServiceAdapter)
}