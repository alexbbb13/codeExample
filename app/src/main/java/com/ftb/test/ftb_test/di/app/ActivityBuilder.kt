package com.ftb.test.ftb_test.di.app

import android.app.LauncherActivity
import com.ftb.test.ftb_test.di.matches.MatchesScreenModule
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeLauncherActivityInjector(): LauncherActivity

}