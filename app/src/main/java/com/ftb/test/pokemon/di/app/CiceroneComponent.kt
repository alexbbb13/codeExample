package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.navigation.AppRouter
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Component(modules = [(CiceroneModule::class)])
@Singleton
interface CiceroneComponent {
    fun getCicerone(): Cicerone<AppRouter>
}