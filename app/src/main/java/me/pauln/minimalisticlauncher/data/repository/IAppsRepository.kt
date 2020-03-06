package me.pauln.minimalisticlauncher.data.repository

import io.reactivex.Single
import me.pauln.minimalisticlauncher.data.model.App

interface IAppsRepository {

    fun getInstalledApps(): List<App>

    fun getUserSelectedApps(): Single<List<App>>
}