package com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels;

import com.ideotekno.framework.mvvm.data.DataManager;
import com.ideotekno.framework.mvvm.ui.base.BaseViewModel;
import com.ideotekno.framework.mvvm.ui.mainmenu.navigator.features.mainhome.ListHomeNav;
import com.ideotekno.framework.mvvm.utils.rx.SchedulerProvider;

public class ListHomeViewModel extends BaseViewModel<ListHomeNav> {
    public ListHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
