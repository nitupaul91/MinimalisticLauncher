package me.pauln.minimalisticlauncher.data.repository

interface IClockRepository {

    fun getTwentyFourHoursClock(): String

    fun getTwelveHoursClock(): String

    fun getDate(): String
}