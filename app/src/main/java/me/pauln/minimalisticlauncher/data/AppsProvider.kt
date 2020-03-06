package me.pauln.minimalisticlauncher.data

import android.content.Intent.CATEGORY_LAUNCHER
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import me.pauln.minimalisticlauncher.MinimalisticLauncherApp
import me.pauln.minimalisticlauncher.data.model.App
import timber.log.Timber
import javax.inject.Inject


class AppsProvider @Inject constructor(
    private val minimalisticLauncherApp: MinimalisticLauncherApp
) {

    fun getInstalledApps(): List<App> {

        val packageManager = minimalisticLauncherApp.packageManager
        val appPackages: MutableList<String> = ArrayList()
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {

            if (!isSystemPackage(packageInfo))
                appPackages.add(packageInfo.packageName)
        }

        val apps = mutableListOf<App>()
        try {
            appPackages.apply {
                distinctBy { it }.forEach { appPackage ->
                    try {
                        val appInfo = packageManager.getApplicationInfo(
                            appPackage,
                            PackageManager.GET_META_DATA
                        )
                        // extract the app name
                        val appLabel = packageManager.getApplicationLabel(appInfo)
                        // extract the app icon
                        val appIcon = packageManager.getApplicationIcon(appInfo)
                        // extract the app launch intent
                        val appLaunchIntent = packageManager.getLaunchIntentForPackage(appPackage)
                        appLaunchIntent?.addCategory(CATEGORY_LAUNCHER)
                        // add the application info to our collection
                        apps.add(
                            App(
                                appPackage,
                                appLabel.toString(),
                                appIcon,
                                appLaunchIntent,
                                false
                            )
                        )
                    } catch (nameNotFoundException: PackageManager.NameNotFoundException) {
                        Timber.w("Package '$appPackage' not found on device, skipping")
                    }
                }

                // now let's sort the apps by app label
//                apps.sortBy { it.label.toLowerCase() }
            }
        } catch (ex: Exception) {
            Timber.w(ex)
        }

        // return to caller
        return apps
    }

    private fun isSystemPackage(appInfo: ApplicationInfo): Boolean {
        return appInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
}