package me.pauln.minimalisticlauncher.data.repository

import me.pauln.minimalisticlauncher.usecases.DateTimeFormatter
import javax.inject.Inject

class ClockRepository @Inject constructor(
    private val dateTimeFormatter: DateTimeFormatter
) : IClockRepository {

    override fun getTwentyFourHoursClock(): String {
        return dateTimeFormatter.formatToTwentyFourHoursClock()
    }

    override fun getTwelveHoursClock(): String {
        return dateTimeFormatter.formatToTwelveHoursClock()
    }

    override fun getDate(): String {
        return dateTimeFormatter.formatCurrentDate()
    }
}