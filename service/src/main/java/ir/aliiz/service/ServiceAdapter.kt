package ir.aliiz.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.aliiz.data.repo.Service
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceAdapter() : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    var items: List<Service> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder =
        ServiceViewHolder.Service(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_service, parent, false
            )
        )

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        when (holder) {
            is ServiceViewHolder.Service -> {
                with(holder.itemView) {
                    val item = items[position]
                    textServiceItemTitle.text = item.title
                    Glide.with(imageServiceItemIcon).load(context.getImage(item.icon))
                }
            }
        }

    }

    private fun Context.getImage(value: String) = resources.getIdentifier(value, "drawable", packageName)

    sealed class ServiceViewHolder(view: View): RecyclerView.ViewHolder(view) {
        class Service(view: View): ServiceViewHolder(view)
    }
}