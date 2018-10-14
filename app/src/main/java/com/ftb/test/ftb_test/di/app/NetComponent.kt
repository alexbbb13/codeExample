package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.navigation.AppRouter
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetComponent {
    fun getNetComponent:

}