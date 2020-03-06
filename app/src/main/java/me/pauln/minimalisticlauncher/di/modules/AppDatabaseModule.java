package me.pauln.minimalisticlauncher.di.modules;

import dagger.Module;
import dagger.Provides;
import me.pauln.minimalisticlauncher.MinimalisticLauncherApp;
import me.pauln.minimalisticlauncher.data.AppsSelectionDatabase;

@Module
public class AppDatabaseModule {

    @Provides
    static AppsSelectionDatabase appsSelectionDatabase(MinimalisticLauncherApp minimalisticLauncherApp) {
        return AppsSelectionDatabase.Companion.getInstance(minimalisticLauncherApp.getApplicationContext());
    }
}
