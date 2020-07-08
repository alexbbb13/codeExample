package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.navigation.AppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class CiceroneModule {
    @Provides
    @Singleton
    fun provideCicerone(router: AppRouter): Cicerone<AppRouter> {
        return Cicerone.create(router)
    }

    @Provides
    @Singleton
    fun provideRouter(): AppRouter {
        return AppRouter()
    }
}