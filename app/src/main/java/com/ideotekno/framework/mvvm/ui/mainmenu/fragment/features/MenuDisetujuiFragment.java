/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.ideotekno.framework.mvvm.ui.mainmenu.fragment.features;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.ideotekno.framework.mvvm.BR;
import com.ideotekno.framework.mvvm.R;
import com.ideotekno.framework.mvvm.ViewModelProviderFactory;
import com.ideotekno.framework.mvvm.databinding.FragmentListHomeBinding;
import com.ideotekno.framework.mvvm.ui.base.BaseFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.navigator.features.mainhome.ListHomeNav;
import com.ideotekno.framework.mvvm.ui.mainmenu.viewmodels.ListHomeViewModel;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 10/07/17.
 */

public class MenuDisetujuiFragment extends BaseFragment<FragmentListHomeBinding, ListHomeViewModel> implements ListHomeNav {
    FragmentListHomeBinding dataBinding;

    @Inject
    ViewModelProviderFactory factory;
    private ListHomeViewModel mViewModel;

    public static MenuDisetujuiFragment newInstance() {
        Bundle args = new Bundle();
        MenuDisetujuiFragment fragment = new MenuDisetujuiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_home;
    }

    @Override
    public ListHomeViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, factory).get(ListHomeViewModel.class);
        return mViewModel;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        dataBinding.rvListNotification.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        dataBinding.rvListNotification.setItemAnimator(new DefaultItemAnimator());
//        dataBinding.rvListNotification.setAdapter(mBlogAdapter);
    }


    @Override
    public void handleError(Throwable throwable, String TAG) {

    }

    @Override
    public void handleErrorNetwork(ANError anError, String TAG) {

    }

    @Override
    public void stopCompositeDisposable() {

    }
}
