package com.ftb.test.ftb_test.application

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.facebook.stetho.Stetho
import com.ftb.test.ftb_test.di.app.DaggerMyApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class FtbApplication  : Application(),
        HasActivityInjector,
        HasSupportFragmentInjector
{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

      @Inject
      lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
      override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent.builder().application(this).build().inject(this);
        Stetho.initializeWithDefaults(this);
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}
