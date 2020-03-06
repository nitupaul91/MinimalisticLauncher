package me.pauln.minimalisticlauncher.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import me.pauln.minimalisticlauncher.MinimalisticLauncherApp
import me.pauln.minimalisticlauncher.data.AppsProvider
import me.pauln.minimalisticlauncher.data.AppsSelectionDao
import me.pauln.minimalisticlauncher.data.AppsSelectionDatabase
import me.pauln.minimalisticlauncher.data.model.App
import javax.inject.Inject

class AppsRepository @Inject constructor(
    private val appsProvider: AppsProvider,
    minimalisticLauncherApp: MinimalisticLauncherApp,
    private var appsSelectionDao: AppsSelectionDao

) : IAppsRepository {

    init {
        val database: AppsSelectionDatabase =
            AppsSelectionDatabase.getInstance(minimalisticLauncherApp.applicationContext)!!
        appsSelectionDao = database.appsDao()
        appsSelectionDao.getApps()
    }

    override fun getAppByPackage(packageName: String): App? {
        val apps = getInstalledApps()
        for (app in apps) {
            if (app.appPackage == packageName) {
                return app
            }
        }
        return null
    }

    override fun getInstalledApps(): List<App> {
        return appsProvider.getInstalledApps()
    }

    override fun getUserSelectedApps(): Single<List<App>> {
        return appsSelectionDao.getApps()
    }

    override fun saveApps(apps: List<App>): Completable {
        return appsSelectionDao.saveApps(apps)
    }
}