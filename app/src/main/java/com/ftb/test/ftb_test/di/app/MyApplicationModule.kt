package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.ui.app.LauncherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MyApplicationModule {
    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): LauncherActivity
}