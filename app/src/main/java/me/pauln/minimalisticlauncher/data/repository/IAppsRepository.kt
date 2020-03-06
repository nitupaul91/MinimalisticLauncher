package me.pauln.minimalisticlauncher.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import me.pauln.minimalisticlauncher.data.model.App

interface IAppsRepository {

    fun getInstalledApps(): List<App>

    fun getUserSelectedApps(): Single<List<App>>

    fun saveApps(apps: List<App>): Completable

    fun getAppByPackage(packageName: String): App?
}