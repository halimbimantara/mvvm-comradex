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

package com.ideotekno.framework.mvvm.di.builder;

import com.ideotekno.framework.mvvm.ui.about.AboutFragmentProvider;
import com.ideotekno.framework.mvvm.ui.feed.FeedActivity;
import com.ideotekno.framework.mvvm.ui.feed.FeedActivityModule;
import com.ideotekno.framework.mvvm.ui.feed.blogs.BlogFragmentProvider;
import com.ideotekno.framework.mvvm.ui.feed.opensource.OpenSourceFragmentProvider;
import com.ideotekno.framework.mvvm.ui.login.LoginActivity;
import com.ideotekno.framework.mvvm.ui.main.MainActivity;
import com.ideotekno.framework.mvvm.ui.main.rating.RateUsDialogProvider;
import com.ideotekno.framework.mvvm.ui.mainmenu.activity.features.MainMenuActivity;
import com.ideotekno.framework.mvvm.ui.mainmenu.components.modules.TabMenuActivityModule;
import com.ideotekno.framework.mvvm.ui.mainmenu.components.providers.TabMenuFragmentProvider;
import com.ideotekno.framework.mvvm.ui.navigator.MainNavigatorActivity;
import com.ideotekno.framework.mvvm.ui.navigator.component.providers.MainNavProviders;
import com.ideotekno.framework.mvvm.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by amitshekhar on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class})
    abstract FeedActivity bindFeedActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {
            AboutFragmentProvider.class,
            RateUsDialogProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {MainNavProviders.class})
    abstract MainNavigatorActivity bindMainNavActivity();

    @ContributesAndroidInjector(modules = {TabMenuActivityModule.class, TabMenuFragmentProvider.class})
    abstract MainMenuActivity bindMainMenuActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
