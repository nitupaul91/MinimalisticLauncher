package me.pauln.minimalisticlauncher.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.pauln.minimalisticlauncher.MinimalisticLauncherApp;
import me.pauln.minimalisticlauncher.di.modules.CustomiseAppsModule;
import me.pauln.minimalisticlauncher.di.modules.HomeModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        HomeModule.class,
        CustomiseAppsModule.class})

public interface AppComponent extends AndroidInjector<MinimalisticLauncherApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MinimalisticLauncherApp> {
    }
}
