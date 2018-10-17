package com.ftb.test.ftb_test.application

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.ftb.test.ftb_test.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class FtbApplication  : Application(),
        HasActivityInjector,
        HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return DaggerAppComponent.builder().create(this)
//    }

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)


        // ловим недоставленные ошибки после очистки CompositeDisposable
        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable !is UndeliverableException) {
                val currentThread = Thread.currentThread()
                currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, throwable)
            }
        }
    }

}
