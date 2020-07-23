package com.ftb.test.pokemon.di.matches

import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.interactors.PokemonsInteractorImpl
import com.ftb.test.pokemon.navigation.AppRouter
import com.ftb.test.pokemon.presenters.PokemonsPresenter
import com.ftb.test.pokemon.repositories.PokemonsRepository
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class PokemonsScreenModule {

    @Provides
    fun providePokemonsPresenter(interactor: PokemonsInteractor, cicerone: Cicerone<AppRouter>) = PokemonsPresenter(interactor, cicerone)

    @Provides
    fun providePokemonsInteractor(repositoryPokemons: PokemonsRepository)  : PokemonsInteractor
            = PokemonsInteractorImpl(repositoryPokemons)

}