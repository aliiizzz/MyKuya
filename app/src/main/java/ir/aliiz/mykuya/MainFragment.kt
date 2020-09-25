package ir.aliiz.mykuya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_main, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomMainNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> true.also {
                    Navigation.findNavController(activity!!, R.id.containerMainHolder).setGraph(R.navigation.service_nav_graph)
                }
                else -> false
            }
        }
    }
}