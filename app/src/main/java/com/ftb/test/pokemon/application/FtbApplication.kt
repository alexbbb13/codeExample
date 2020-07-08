package com.ftb.test.pokemon.application

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.facebook.stetho.Stetho
import com.ftb.test.pokemon.di.app.DaggerMyApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class FtbApplication  : Application(),
        HasActivityInjector,
        HasSupportFragmentInjector
{

    companion object {
        lateinit var INSTANCE: FtbApplication
    }
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

      @Inject
      lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
      override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = supportFragmentInjector

    private var cicerone: Cicerone<Router>? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initCicerone()
        DaggerMyApplicationComponent.builder().application(this).build().inject(this);
        Stetho.initializeWithDefaults(this);
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone!!.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone!!.router
    }

}
