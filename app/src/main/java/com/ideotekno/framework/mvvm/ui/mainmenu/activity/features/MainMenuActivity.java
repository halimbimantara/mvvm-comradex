package com.ideotekno.framework.mvvm.ui.mainmenu.activity.features;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
        initToolbar();
        setUp();
    }

    private void initToolbar() {
        mToolbar = dataBinding.Maintoolbar;
        dataBinding.Maintoolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void setUp() {
        setSupportActionBar(dataBinding.Maintoolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setupViewPager(dataBinding.viewPager);
        try {
            TabLayout.Tab tab1 = dataBinding.tabLayout.getTabAt(0);
            if (tab1 != null) {
                tab1.setIcon(R.drawable.ic_tab_terbaru);
                if (tab1.getIcon() != null) {
                    tab1.getIcon().setColorFilter(ContextCompat.getColor(this, R.color.md_grey_200),
                            PorterDuff.Mode.SRC_IN);
                }
            }
            TabLayout.Tab tab2 = dataBinding.tabLayout.getTabAt(1);
            if (tab2 != null) {
                tab2.setIcon(R.drawable.ic_tab_ditolak);
                if (tab2.getIcon() != null) {
                    tab2.getIcon().setColorFilter(ContextCompat.getColor(this, R.color.md_grey_200),
                            PorterDuff.Mode.SRC_IN);
                }
            }
            TabLayout.Tab tab3 = dataBinding.tabLayout.getTabAt(2);
            if (tab3 != null) {
                tab3.setIcon(R.drawable.ic_tab_disetujui);
                if (tab3.getIcon() != null) {
                    tab3.getIcon().setColorFilter(ContextCompat.getColor(this, R.color.md_grey_200),
                            PorterDuff.Mode.SRC_IN);
                }
            }
            TabLayout.Tab tab4 = dataBinding.tabLayout.getTabAt(3);
            if (tab4 != null) {
                tab4.setIcon(R.drawable.ic_tab_reschedule);
                if (tab4.getIcon() != null) {
                    tab4.getIcon().setColorFilter(ContextCompat.getColor(this, R.color.md_grey_200),
                            PorterDuff.Mode.SRC_IN);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_tab_terbaru)));
//        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_tab_ditolak)));
//        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_tab_disetujui)));
//        dataBinding.tabLayout.addTab(dataBinding.tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.ic_tab_reschedule)));
//

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
        menuPagerAdapter.setCount(4);
        Fragment fragment1 = new MenuNotifBaruFragment();
        Fragment fragment2 = new MenuDisetujuiFragment();
        Fragment fragment3 = new MenuDitolakFragment();
        Fragment fragment4 = new MenuRescheduleFragment();

        menuPagerAdapter.addFragment(fragment1, "Terbaru");
        menuPagerAdapter.addFragment(fragment2, "Ditolak");
        menuPagerAdapter.addFragment(fragment3, "Disetujui");
        menuPagerAdapter.addFragment(fragment4, "Reschedule");
        dataBinding.tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(menuPagerAdapter);

    }
}
