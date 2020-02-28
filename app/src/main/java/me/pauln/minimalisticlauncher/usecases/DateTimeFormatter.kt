package me.pauln.minimalisticlauncher.usecases

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTimeFormatter @Inject constructor() {

    fun formatToTwentyFourHoursClock(): String {
        return SimpleDateFormat("H:mm", Locale.ROOT).format(Date())
    }

    fun formatCurrentDate(): String {
        return SimpleDateFormat("EEE, MMM dd", Locale.ROOT).format(Date())
    }

    fun formatToTwelveHoursClock(): String {
        return SimpleDateFormat("h:mm aa", Locale.ROOT).format(Date())
    }
}
