package com.ftb.test.ftb_test.di.app

import android.app.Application
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.di.matches.MatchesScreenModule
import com.ftb.test.ftb_test.di.results.ResultsScreenModule
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [

    AppModule::class,
    MyApplicationModule::class,
//    ExecutorsModule::class,
    NetworkModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    NavigationModule::class,
//    MatchesScreenModule::class,
//    ResultsScreenModule::class,
//    LocalizationModule::class,
     MatchesModule::class,
    ResultsModule::class,
//    LoanModule::class,
//    DepositModule::class,
    CiceroneModule::class,
    //RetrofitModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class,
            AndroidInjectionModule::class

])
interface MyApplicationComponent : AndroidInjector<FtbApplication> {

//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<FtbApplication>()

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MyApplicationComponent
    }
}
