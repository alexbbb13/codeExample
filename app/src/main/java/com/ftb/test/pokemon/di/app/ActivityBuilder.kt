package com.ftb.test.pokemon.di.app

import android.app.LauncherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeLauncherActivityInjector(): LauncherActivity

}