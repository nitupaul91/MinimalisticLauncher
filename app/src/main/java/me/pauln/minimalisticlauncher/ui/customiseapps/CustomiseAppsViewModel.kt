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
    val isAddButtonVisible = MutableLiveData<Boolean>().apply { this.value = false }

    private val selectedAppList: MutableList<App> = ArrayList()

    init {
        getInstalledApps()
    }

    fun onAppClicked(clickedApp: App) {
        if (clickedApp.isSelected) {
            showAddButton(true)
            selectedAppList.add(clickedApp)
        } else {
            selectedAppList.remove(clickedApp)
        }
        if (selectedAppList.isEmpty()) {
            showAddButton(false)
        }
    }

    private fun showAddButton(show: Boolean) {
        isAddButtonVisible.value = show
    }

    private fun getInstalledApps() {
        apps.value = appsRepository.getInstalledApps()
    }
}
