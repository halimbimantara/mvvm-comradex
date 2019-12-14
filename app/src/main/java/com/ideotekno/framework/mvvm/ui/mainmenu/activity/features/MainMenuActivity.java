package com.ideotekno.framework.mvvm.ui.mainmenu.activity.features;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ideotekno.framework.mvvm.R;
import com.ideotekno.framework.mvvm.ViewModelProviderFactory;
import com.ideotekno.framework.mvvm.databinding.MainHomeBinding;
import com.ideotekno.framework.mvvm.ui.base.BaseActivity;
import com.ideotekno.framework.mvvm.ui.mainmenu.adapter.MenuPagerAdapter;
import com.ideotekno.framework.mvvm.ui.mainmenu.fragment.features.MenuDisetujuiFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.fragment.features.MenuDitolakFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.fragment.features.MenuNotifBaruFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.fragment.features.MenuRescheduleFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels.MainMenuViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainMenuActivity extends BaseActivity<MainHomeBinding, MainMenuViewModel> implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    MenuPagerAdapter menuPagerAdapter;
    private MainMenuViewModel mViewModel;
    private MainHomeBinding dataBinding;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainMenuActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return com.ideotekno.framework.mvvm.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_home;
    }

    @Override
    public MainMenuViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, factory).get(MainMenuViewModel.class);
        return mViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        setSupportActionBar(dataBinding.Maintoolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setupViewPager(dataBinding.viewPager);
        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_terbaru));
        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_ditolak));
        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_disetujui));
        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_reschedule));
        dataBinding.viewPager.setOffscreenPageLimit(dataBinding.tabLayout.getTabCount());
        dataBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dataBinding.tabLayout));
        dataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dataBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    void setupViewPager(ViewPager mViewPager) {
        Fragment fragment1 = new MenuNotifBaruFragment();
        Fragment fragment2 = new MenuDisetujuiFragment();
        Fragment fragment3 = new MenuDitolakFragment();
        Fragment fragment4 = new MenuRescheduleFragment();

        menuPagerAdapter.addFragment(fragment1, "Terbaru");
        menuPagerAdapter.addFragment(fragment2, "Ditolak");
        menuPagerAdapter.addFragment(fragment3, "Disetujui");
        menuPagerAdapter.addFragment(fragment4, "Reschedule");

        menuPagerAdapter.setCount(4);
        mViewPager.setAdapter(menuPagerAdapter);
    }
}
