package com.ftb.test.ftb_test.di.app

import android.app.Application
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.di.matches.MatchesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
//    ExecutorsModule::class,
//    NetworkModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    NavigationModule::class,
//    LocalizationModule::class,
//     MatchesModule::class,
//    LoanModule::class,
//    DepositModule::class,
    CiceroneModule::class,
    RetrofitModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<FtbApplication> {

    override fun inject(instance: FtbApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
