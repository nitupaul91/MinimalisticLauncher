package me.pauln.minimalisticlauncher.ui.customiseapps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.data.repository.AppsRepository
import javax.inject.Inject

class CustomiseAppsViewModel @Inject constructor(
    private val appsRepository: AppsRepository
) : ViewModel() {

    val apps = MutableLiveData<List<App>>()

    init {
        getInstalledApps()
    }

    fun getInstalledApps() {
        apps.value = appsRepository.getInstalledApps()
    }
}
