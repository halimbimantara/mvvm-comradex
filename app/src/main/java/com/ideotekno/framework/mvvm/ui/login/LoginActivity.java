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

package com.ideotekno.framework.mvvm.ui.login;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.ideotekno.framework.mvvm.BR;
import com.ideotekno.framework.mvvm.R;
import com.ideotekno.framework.mvvm.ViewModelProviderFactory;
import com.ideotekno.framework.mvvm.databinding.ActivityAccountBinding;
import com.ideotekno.framework.mvvm.databinding.ActivityLoginBinding;
import com.ideotekno.framework.mvvm.ui.base.BaseActivity;
import com.ideotekno.framework.mvvm.ui.main.MainActivity;
import com.ideotekno.framework.mvvm.ui.mainmenu.activity.features.MainMenuActivity;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 08/07/17.
 */

public class LoginActivity extends BaseActivity<ActivityAccountBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityAccountBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void login() {
        String email = mActivityLoginBinding.editText4.getText().toString();
        String password = mActivityLoginBinding.editText7.getText().toString();
        if (mLoginViewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard();
            mLoginViewModel.login(email, password);
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainMenuActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
    }
}
