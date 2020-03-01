package me.pauln.minimalisticlauncher.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.data.repository.AppsRepository
import me.pauln.minimalisticlauncher.data.repository.ClockRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val clockRepository: ClockRepository,
    private val appsRepository: AppsRepository
) : ViewModel() {

    val apps = MutableLiveData<List<App>>()
    val currentTime = MutableLiveData<String>()
    val currentDate = MutableLiveData<String>()
    val app = MutableLiveData<App>()

    fun getContent() {
        getTwentyFourHoursClock()
        getInstalledApps()
    }

    fun getTwentyFourHoursClock() {
        currentTime.value = clockRepository.getTwentyFourHoursClock()
        getDate()
    }

    fun getTwelveHoursClock() {
        currentTime.value = clockRepository.getTwelveHoursClock()
        getDate()
    }

    fun getInstalledApps() {
        apps.value = appsRepository.getInstalledApps()
    }

    private fun getDate() {
        currentDate.value = clockRepository.getDate()
    }

    fun openApp(app: App) {
        this.app.value = app
    }
}
