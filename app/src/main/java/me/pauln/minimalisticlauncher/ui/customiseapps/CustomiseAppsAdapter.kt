package me.pauln.minimalisticlauncher.ui.customiseapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.databinding.CustomiseAppListItemBinding

class CustomiseAppsAdapter(
    private val viewModel: CustomiseAppsViewModel,
    private val appClickListener: OnAppClickListener

) : RecyclerView.Adapter<CustomiseAppsAdapter.ViewHolder>() {

    private var apps: List<App> = ArrayList()

    fun setApps(apps: List<App>) {
        this.apps = apps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomiseAppListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apps[position], appClickListener)
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(app: App, appClickListener: OnAppClickListener) {
            val binding: CustomiseAppListItemBinding = DataBindingUtil.getBinding(itemView)!!
            binding.app = app
            binding.viewModel = viewModel
            itemView.setOnClickListener {
                if (!app.isSelected){
                    binding.checkMarkIv.visibility = View.VISIBLE
                    app.isSelected = true
                    appClickListener.onAppClicked(app)
                } else {
                    app.isSelected = false
                    binding.checkMarkIv.visibility = View.INVISIBLE
                }
            }
        }
    }

    interface OnAppClickListener {
        fun onAppClicked(app: App)
    }
}