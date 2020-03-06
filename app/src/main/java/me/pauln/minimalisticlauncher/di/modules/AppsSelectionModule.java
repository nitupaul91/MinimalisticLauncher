package me.pauln.minimalisticlauncher.di.modules;

import dagger.Module;
import dagger.Provides;
import me.pauln.minimalisticlauncher.data.AppsSelectionDao;
import me.pauln.minimalisticlauncher.data.AppsSelectionDatabase;

@Module
public abstract class AppsSelectionModule {

    @Provides
    static AppsSelectionDao appsSelectionDao(AppsSelectionDatabase appsSelectionDatabase) {
        return appsSelectionDatabase.appsDao();
    }
}
