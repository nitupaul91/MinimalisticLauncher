package me.pauln.minimalisticlauncher.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import me.pauln.minimalisticlauncher.R
import me.pauln.minimalisticlauncher.databinding.HomeFragmentBinding
import me.pauln.minimalisticlauncher.di.ViewModelFactory
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>

    private lateinit var receiver: BroadcastReceiver
    private lateinit var adapter: HomeAppsAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        receiver = ClockReceiver()

        viewModel.getTwentyFourHoursClock()

        adapter = HomeAppsAdapter()
        binding = HomeFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.setLifecycleOwner { lifecycle }
        binding.appListRv.adapter = adapter
        binding.appListRv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentTime.observe(this, Observer { time ->
            timeTv.text = time
        })

        viewModel.currentDate.observe(this, Observer { date ->
            dateTv.text = date
        })
//todo refactor code
        phoneIv.setOnClickListener { _ ->
            try {
                val pm = context?.packageManager ?: return@setOnClickListener
                val intent = Intent(Intent.ACTION_DIAL)
                val componentName = intent.resolveActivity(pm)
                if (componentName == null) startActivity(intent) else
                    pm.getLaunchIntentForPackage(componentName.packageName)?.let {
                        startActivity(it)
                    } ?: run { startActivity(intent) }
            } catch (e: Exception) {
                // Do nothing
            }
        }

        cameraIv.setOnClickListener {
            try {
                val intent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
                startActivity(intent)
            } catch (e: Exception) {
                // Do nothing
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(receiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(receiver)
    }

    inner class ClockReceiver : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            viewModel.getTwentyFourHoursClock()
        }
    }
}
