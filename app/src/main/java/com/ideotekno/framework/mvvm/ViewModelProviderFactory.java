package com.ideotekno.framework.mvvm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ideotekno.framework.mvvm.data.DataManager;
import com.ideotekno.framework.mvvm.ui.about.AboutViewModel;
import com.ideotekno.framework.mvvm.ui.feed.FeedViewModel;
import com.ideotekno.framework.mvvm.ui.feed.blogs.BlogViewModel;
import com.ideotekno.framework.mvvm.ui.feed.opensource.OpenSourceViewModel;
import com.ideotekno.framework.mvvm.ui.login.LoginViewModel;
import com.ideotekno.framework.mvvm.ui.main.MainViewModel;
import com.ideotekno.framework.mvvm.ui.main.rating.RateUsViewModel;
import com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels.ListHomeViewModel;
import com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels.MainMenuViewModel;
import com.ideotekno.framework.mvvm.ui.navigator.viewmodels.MainNavigatorViewModel;
import com.ideotekno.framework.mvvm.ui.splash.SplashViewModel;
import com.ideotekno.framework.mvvm.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AboutViewModel.class)) {
            //noinspection unchecked
            return (T) new AboutViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FeedViewModel.class)) {
            //noinspection unchecked
            return (T) new FeedViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BlogViewModel.class)) {
            //noinspection unchecked
            return (T) new BlogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RateUsViewModel.class)) {
            //noinspection unchecked
            return (T) new RateUsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OpenSourceViewModel.class)) {
            //noinspection unchecked
            return (T) new OpenSourceViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
            //main nav
        } else if (modelClass.isAssignableFrom(MainNavigatorViewModel.class)) {
            //noinspection unchecked
            return (T) new MainNavigatorViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ListHomeViewModel.class)) {
            //noinspection unchecked
            return (T) new ListHomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainMenuViewModel.class)) {
            //noinspection unchecked
            return (T) new MainMenuViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}