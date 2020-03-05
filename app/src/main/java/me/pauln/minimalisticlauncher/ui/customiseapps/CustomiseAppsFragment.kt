package me.pauln.minimalisticlauncher.ui.customiseapps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.customise_apps_fragment.*
import me.pauln.minimalisticlauncher.R
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.databinding.CustomiseAppsFragmentBinding
import me.pauln.minimalisticlauncher.di.ViewModelFactory
import javax.inject.Inject

class CustomiseAppsFragment : DaggerFragment(), CustomiseAppsAdapter.OnAppClickListener {

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

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CustomiseAppsViewModel::class.java)

        adapter = CustomiseAppsAdapter(viewModel, this)
        binding = CustomiseAppsFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.setLifecycleOwner { lifecycle }
        binding.customiseAppsRv.adapter = adapter
        binding.customiseAppsRv.layoutManager = GridLayoutManager(context, 4)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn.setOnClickListener {
            navigateToHome(view)
        }
    }

    override fun onAppClicked(app: App) {
        viewModel.onAppClicked(app)
    }

    fun navigateToHome(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_customiseAppsFragment_to_homeFragment)
    }
}
