package com.ftb.test.ftb_test.di.app

import android.app.LauncherActivity
import com.ftb.test.ftb_test.di.matches.MatchesScreenModule
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {

    //    @ContributesAndroidInjector
//    abstract fun bindMBActivity(): SovcomActivity
//    @ContributesAndroidInjector
//    abstract fun bindMBFragmentContainerActivity(): SovcomFragmentContainerActivity
    /**
     * Example:
     * @ContributesAndroidInjector(modules = [NameModule::class])
     * abstract fun bindNameActivity(): NameActivity
     * */

    @ContributesAndroidInjector(modules = [MatchesScreenModule::class])
    abstract fun contributeLauncherActivityInjector(): LauncherActivity

}