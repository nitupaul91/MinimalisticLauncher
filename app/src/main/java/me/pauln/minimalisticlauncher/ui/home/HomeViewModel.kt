package me.pauln.minimalisticlauncher.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.data.repository.AppsRepository
import me.pauln.minimalisticlauncher.data.repository.ClockRepository
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val clockRepository: ClockRepository,
    private val appsRepository: AppsRepository
) : ViewModel() {

    val apps = MutableLiveData<List<App>>()
    val currentTime = MutableLiveData<String>()
    val currentDate = MutableLiveData<String>()
    val app = MutableLiveData<App>()

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getContent() {
        getTwentyFourHoursClock()
        getUserSelectedApps()
    }

    fun getTwentyFourHoursClock() {
        currentTime.value = clockRepository.getTwentyFourHoursClock()
        getDate()
    }

    fun openApp(app: App) {
        this.app.value = app
    }

    private fun getDate() {
        currentDate.value = clockRepository.getDate()
    }

    private fun getUserSelectedApps() {
        disposables.add(
            appsRepository.getUserSelectedApps()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ selectedApps ->
                    apps.value = selectedApps
                }, { throwable ->
                    Timber.w(throwable)
                })
        )
    }

    @Deprecated("not implemented yet")
    fun getTwelveHoursClock() {
        currentTime.value = clockRepository.getTwelveHoursClock()
        getDate()
    }
}
