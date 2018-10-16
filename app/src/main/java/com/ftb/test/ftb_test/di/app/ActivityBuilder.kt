package com.ftb.test.ftb_test.di.app

import android.app.LauncherActivity
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

    @ContributesAndroidInjector(modules = arrayOf())
    internal abstract fun contributeLauncherActivityInjector(): LauncherActivity

}