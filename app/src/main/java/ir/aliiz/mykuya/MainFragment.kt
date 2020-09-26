package ir.aliiz.mykuya

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import ir.aliiz.navigation.LocationNto
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import java.security.Permission

class MainFragment : Fragment(), MainPresenter.MainView {
    private val mainPresenter: MainPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_main, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainPresenter.attachView(this, viewLifecycleOwner.lifecycle)
        bottomMainNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> true.also {
                    Navigation.findNavController(activity!!, R.id.containerMainHolder).setGraph(R.navigation.service_nav_graph)
                }
                else -> false
            }
        }

    }

    override fun loadMapScreen() {
        findNavController().navigate(MainFragmentDirections.actionMainToMap(LocationNto(35.695614, 51.374594)))
    }
}