package me.pauln.minimalisticlauncher.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.pauln.minimalisticlauncher.ui.home.HomeFragment;

@Module
public abstract class HomeModule {

    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();
}
