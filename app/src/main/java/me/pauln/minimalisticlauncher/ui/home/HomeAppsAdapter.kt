package me.pauln.minimalisticlauncher.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.databinding.AppListItemBinding

class HomeAppsAdapter(
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<HomeAppsAdapter.ViewHolder>() {

    private var apps: List<App> = ArrayList()

    fun setApps(apps: List<App>) {
        this.apps = apps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AppListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apps[position])
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(app: App) {
            val binding: AppListItemBinding = DataBindingUtil.getBinding(itemView)!!
            binding.app = app
            binding.viewModel = viewModel
        }
    }
}