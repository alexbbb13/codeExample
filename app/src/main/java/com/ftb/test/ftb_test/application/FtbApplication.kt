package com.ftb.test.ftb_test.application

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
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
//    @Inject
//    lateinit var activityInjector: AndroidInjector<Activity>
//    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
//
      @Inject
      lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
      override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent.builder().application(this).build().inject(this);

//        DaggerAppComponent
//                .builder()
//                .application(this)
//                .build()
//                .inject(this)
//
//
//        // ловим недоставленные ошибки после очистки CompositeDisposable
//        RxJavaPlugins.setErrorHandler { throwable ->
//            if (throwable !is UndeliverableException) {
//                val currentThread = Thread.currentThread()
//                currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, throwable)
//            }
//        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}
