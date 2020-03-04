package me.pauln.minimalisticlauncher.ui.home

import android.content.*
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import android.provider.Settings
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import me.pauln.minimalisticlauncher.R
import me.pauln.minimalisticlauncher.data.model.App
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

        viewModel.getContent()

        adapter = HomeAppsAdapter(viewModel)
        binding = HomeFragmentBinding.bind(view)
        binding.viewModel = viewModel
        binding.setLifecycleOwner { lifecycle }
        binding.appListRv.adapter = adapter
        binding.appListRv.layoutManager = GridLayoutManager(context, 2)

//todo find another solution to disable back button
        view.setFocusableInTouchMode(true)
        view.requestFocus()
        view.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() === KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        return true
                    }
                }
                return false
            }
        })

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

        viewModel.app.observe(this, Observer { app ->
            openApp(app)
        })

        phoneIv.setOnClickListener {
            navigateToPhone()
        }

        cameraIv.setOnClickListener {
            navigateToCamera()
        }

        dateTv.setOnClickListener {
            navigateToCalendar()
        }

        timeTv.setOnClickListener {
            navigateToAlarm()
        }

        phoneSettingsTv.setOnClickListener {
            val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)
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

    private fun openApp(app: App) {
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            intent.resolveActivity((activity ?: return).packageManager)?.let {
                startActivity(app.launchIntent)
            }
        } catch (e: Exception) {
            // Do no shit yet
        }
    }

    private fun navigateToPhone() {
        try {
            val pm = context!!.packageManager
            val intent = Intent(Intent.ACTION_DIAL)
            val componentName = intent.resolveActivity(pm)
            if (componentName == null) startActivity(intent) else
                pm.getLaunchIntentForPackage(componentName.packageName)?.let {
                    startActivity(it)
                } ?: run { startActivity(intent) }
        } catch (e: Exception) {
            //todo do something
        }
    }

    private fun navigateToAlarm() {
        try {
            val pm = context!!.packageManager
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val componentName = intent.resolveActivity(pm)
            if (componentName == null) startActivity(intent) else
                pm.getLaunchIntentForPackage(componentName.packageName)?.let {
                    startActivity(it)
                }
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun navigateToCalendar() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_CALENDAR)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }
    }

    private fun navigateToCamera() {
        try {
            val intent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
            startActivity(intent)
        } catch (e: Exception) {
            //todo do something
        }
    }

    inner class ClockReceiver : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            viewModel.getTwentyFourHoursClock()
        }
    }
}
