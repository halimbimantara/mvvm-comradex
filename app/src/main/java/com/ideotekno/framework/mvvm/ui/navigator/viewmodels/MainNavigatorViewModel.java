package com.ideotekno.framework.mvvm.ui.navigator.viewmodels;

import com.ideotekno.framework.mvvm.data.DataManager;
import com.ideotekno.framework.mvvm.ui.base.BaseViewModel;
import com.ideotekno.framework.mvvm.utils.rx.SchedulerProvider;

public class MainNavigatorViewModel extends BaseViewModel {
    public MainNavigatorViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
