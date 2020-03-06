package me.pauln.minimalisticlauncher.ui.customiseapps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.data.repository.AppsRepository
import timber.log.Timber
import javax.inject.Inject

class CustomiseAppsViewModel @Inject constructor(
    private val appsRepository: AppsRepository
) : ViewModel() {

    val apps = MutableLiveData<List<App>>()
    val isAddButtonVisible = MutableLiveData<Boolean>().apply { this.value = false }
    val isAppSelectionFinished = MutableLiveData<Boolean>().apply { this.value = false }

    private val selectedAppList: MutableList<App> = ArrayList()
    private val disposables = CompositeDisposable()

    init {
        getInstalledApps()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
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

    fun saveSelectedApps() {
        isAppSelectionFinished.value = true
        disposables.add(
            appsRepository.saveApps(selectedAppList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.w("result cache successful")
                    isAppSelectionFinished.value = false
                }, {
                    Timber.w(it, "result cache error")
                })
        )
    }

    private fun showAddButton(show: Boolean) {
        isAddButtonVisible.value = show
    }

    private fun getInstalledApps() {
        apps.value = appsRepository.getInstalledApps()
    }
}
