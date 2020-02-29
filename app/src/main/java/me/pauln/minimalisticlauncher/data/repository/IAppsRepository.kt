package me.pauln.minimalisticlauncher.data.repository

import me.pauln.minimalisticlauncher.data.model.App

interface IAppsRepository {

    fun getInstalledApps(): List<App>
}