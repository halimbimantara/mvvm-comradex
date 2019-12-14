package com.ideotekno.framework.mvvm.ui.navigator.component.providers;

import com.ideotekno.framework.mvvm.ui.navigator.ui.dashboard.DashboardFragment;
import com.ideotekno.framework.mvvm.ui.navigator.ui.home.HomeFragment;
import com.ideotekno.framework.mvvm.ui.navigator.ui.notifications.NotificationsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainNavProviders {
    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragmentFactory();

    @ContributesAndroidInjector
    abstract DashboardFragment provideDashboardFactory();

    @ContributesAndroidInjector
    abstract NotificationsFragment provideNotifFactory();
}
