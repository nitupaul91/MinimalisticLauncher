package me.pauln.minimalisticlauncher.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pauln.minimalisticlauncher.data.model.App
import me.pauln.minimalisticlauncher.data.repository.ClockRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val clockRepository: ClockRepository
) : ViewModel() {

    val apps = MutableLiveData<List<App>>()
    val currentTime = MutableLiveData<String>()
    val currentDate = MutableLiveData<String>()

    fun getTwentyFourHoursClock() {
        currentTime.value = clockRepository.getTwentyFourHoursClock()
        getDate()
    }

    fun getTwelveHoursClock() {
        currentTime.value = clockRepository.getTwelveHoursClock()
        getDate()
    }

    private fun getDate() {
        currentDate.value = clockRepository.getDate()
    }
}
