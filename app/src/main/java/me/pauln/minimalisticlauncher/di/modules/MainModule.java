package me.pauln.minimalisticlauncher.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.pauln.minimalisticlauncher.ui.MainActivity;

@Module
public abstract class MainModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
