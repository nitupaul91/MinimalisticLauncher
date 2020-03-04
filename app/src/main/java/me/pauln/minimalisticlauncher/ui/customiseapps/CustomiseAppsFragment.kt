package me.pauln.minimalisticlauncher.ui.customiseapps

import android.content.BroadcastReceiver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import me.pauln.minimalisticlauncher.R
import me.pauln.minimalisticlauncher.databinding.CustomiseAppsFragmentBinding
import me.pauln.minimalisticlauncher.databinding.HomeFragmentBinding
import me.pauln.minimalisticlauncher.di.ViewModelFactory
import me.pauln.minimalisticlauncher.ui.home.HomeAppsAdapter
import me.pauln.minimalisticlauncher.ui.home.HomeViewModel
import javax.inject.Inject

class CustomiseAppsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CustomiseAppsViewModel>

    private lateinit var adapter: CustomiseAppsAdapter
    private lateinit var viewModel: CustomiseAppsViewModel
    private lateinit var binding: CustomiseAppsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customise_apps_fragment, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CustomiseAppsViewModel::class.java)

        adapter = CustomiseAppsAdapter(viewModel)
        binding = CustomiseAppsFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.setLifecycleOwner { lifecycle }
        binding.customiseAppsRv.adapter = adapter
        binding.customiseAppsRv.layoutManager = GridLayoutManager(context, 4)

        return view
    }
}
