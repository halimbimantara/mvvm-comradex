package com.ideotekno.framework.mvvm.ui.navigator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ideotekno.framework.mvvm.R;
import com.ideotekno.framework.mvvm.ViewModelProviderFactory;
import com.ideotekno.framework.mvvm.databinding.ActivityMain2Binding;
import com.ideotekno.framework.mvvm.ui.base.BaseActivity;
import com.ideotekno.framework.mvvm.ui.navigator.viewmodels.MainNavigatorViewModel;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;

public class MainNavigatorActivity extends BaseActivity<ActivityMain2Binding, MainNavigatorViewModel> {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private MainNavigatorViewModel mViewModel;
    private ActivityMain2Binding dataBinding;

    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainNavigatorActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return R.layout.activity_main2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public MainNavigatorViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, factory).get(MainNavigatorViewModel.class);
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = getViewDataBinding();
        initUi();
        setmToolbar();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(dataBinding.navView, navController);

    }

    void initUi() {
        mToolbar = dataBinding.toolbar;
    }

    private void setmToolbar() {
        setSupportActionBar(mToolbar);
    }
//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return fragmentDispatchingAndroidInjector;
//    }
}
