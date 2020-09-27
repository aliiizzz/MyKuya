package ir.aliiz.mykuya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import ir.aliiz.data.repo.LatLng
import ir.aliiz.navigation.LocationNto
import ir.aliiz.service.toLocationNto
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject

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
        val navhost = childFragmentManager.fragments[0] as NavHostFragment
        val controller = navhost.navController
        bottomMainNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> true.also {
                    controller.setGraph(R.navigation.service_nav_graph)
                }
                else -> false
            }
        }

        bottomMainNavigation.selectedItemId = R.id.home
    }

    override fun loadMapScreen() {
        findNavController().navigate(MainFragmentDirections.actionMainToMap(defaultLocation))
    }

    override fun loadMap(location: LatLng) {
        findNavController().navigate(MainFragmentDirections.actionMainToMap(location.toLocationNto()))
    }

    companion object {
        val defaultLocation = LocationNto(35.695614, 51.374594)
    }
}