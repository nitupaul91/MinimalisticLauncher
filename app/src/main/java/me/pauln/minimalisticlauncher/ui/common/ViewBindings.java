package me.pauln.minimalisticlauncher.ui.common;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class ViewBindings {

    @BindingAdapter("visible")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ?
                View.VISIBLE :
                View.INVISIBLE);
    }
}