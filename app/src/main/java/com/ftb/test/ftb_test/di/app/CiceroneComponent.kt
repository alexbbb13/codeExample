package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.navigation.AppRouter
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

/**
 * Created by olegkolt on 12/01/2018.
 */
@Component(modules = [(CiceroneModule::class)])
@Singleton
interface CiceroneComponent {
    fun getCicerone(): Cicerone<AppRouter>
}