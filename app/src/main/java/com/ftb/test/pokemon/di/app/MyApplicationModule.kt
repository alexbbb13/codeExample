package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.ui.app.LauncherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MyApplicationModule {
    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): LauncherActivity
}