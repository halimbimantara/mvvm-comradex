package com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels;

import com.ideotekno.framework.mvvm.data.DataManager;
import com.ideotekno.framework.mvvm.ui.base.BaseViewModel;
import com.ideotekno.framework.mvvm.utils.rx.SchedulerProvider;

public class MainMenuViewModel extends BaseViewModel {
    public MainMenuViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
