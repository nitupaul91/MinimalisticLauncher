package me.pauln.minimalisticlauncher.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.pauln.minimalisticlauncher.ui.customiseapps.CustomiseAppsFragment;

@Module
public abstract class CustomiseAppsModule {

    @ContributesAndroidInjector
    abstract CustomiseAppsFragment customiseAppsFragment();
}
