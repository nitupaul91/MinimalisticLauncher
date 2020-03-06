package me.pauln.minimalisticlauncher

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.pauln.minimalisticlauncher.data.AppsSelectionDatabase
import me.pauln.minimalisticlauncher.di.DaggerAppComponent
import timber.log.Timber

class MinimalisticLauncherApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .create(this)
    }
}