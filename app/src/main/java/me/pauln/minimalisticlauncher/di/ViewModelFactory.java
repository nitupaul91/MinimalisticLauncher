package me.pauln.minimalisticlauncher.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

    private Provider<VM> viewModelProvider;

    @Inject
    public ViewModelFactory(Provider<VM> viewModelProvider) {
        this.viewModelProvider = viewModelProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModelProvider.get();
    }
}
