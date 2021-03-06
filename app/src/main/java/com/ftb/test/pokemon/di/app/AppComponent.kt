package com.ftb.test.pokemon.di.app

import android.app.Application
import com.ftb.test.pokemon.application.FtbApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [

    AppModule::class,
    MyApplicationModule::class,
    NetworkModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    NavigationModule::class,
    PokemonsModule::class,
    PredictionsModule::class,
    ResultsModule::class,
    CiceroneModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class

])
interface MyApplicationComponent : AndroidInjector<FtbApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MyApplicationComponent
    }
}
