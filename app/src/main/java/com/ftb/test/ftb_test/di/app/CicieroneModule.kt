package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.navigation.AppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class CiceroneModule {
    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<AppRouter> {
        return Cicerone.create(AppRouter())
    }
}