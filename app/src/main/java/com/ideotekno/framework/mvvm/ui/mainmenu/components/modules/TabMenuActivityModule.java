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

package com.ideotekno.framework.mvvm.ui.mainmenu.components.modules;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.ideotekno.framework.mvvm.ui.feed.blogs.BlogFragment;
import com.ideotekno.framework.mvvm.ui.mainmenu.activity.features.MainMenuActivity;
import com.ideotekno.framework.mvvm.ui.mainmenu.adapter.MenuPagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amitshekhar on 14/09/17.
 */
@Module
public class TabMenuActivityModule {

    @Provides
    MenuPagerAdapter provideFeedPagerAdapter(MainMenuActivity activity) {
        return new MenuPagerAdapter(activity.getSupportFragmentManager());
    }

}
