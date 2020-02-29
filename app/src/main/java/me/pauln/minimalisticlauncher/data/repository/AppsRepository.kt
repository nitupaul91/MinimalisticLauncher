package me.pauln.minimalisticlauncher.data.repository

import me.pauln.minimalisticlauncher.data.AppsProvider
import me.pauln.minimalisticlauncher.data.model.App
import javax.inject.Inject

class AppsRepository @Inject constructor(
    private val appsProvider: AppsProvider
) : IAppsRepository {

    override fun getInstalledApps(): List<App> {
        return appsProvider.getInstalledApps()
    }
}