package me.pauln.minimalisticlauncher.ui.home;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import me.pauln.minimalisticlauncher.data.model.App;

public class AppListBindings {

    @BindingAdapter("appList")
    public static void setApps(RecyclerView recyclerView, List<App> apps) {
        if (apps == null) {
            return;
        }
        HomeAppsAdapter adapter = (HomeAppsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setApps(apps);
        }
    }
}
