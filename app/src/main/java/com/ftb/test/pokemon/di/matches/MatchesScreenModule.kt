package com.ftb.test.pokemon.di.matches

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.interactors.PokemonsInteractorImpl
import com.ftb.test.pokemon.navigation.AppRouter
import com.ftb.test.pokemon.presenters.MatchesPresenter
import com.ftb.test.pokemon.repositories.PokemonsRepository
import com.ftb.test.pokemon.repositories.PredictionsRepository
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class MatchesScreenModule {

    @Provides
    fun provideMatchesPresenter(interactor: PokemonsInteractor, cicerone: Cicerone<AppRouter>) = MatchesPresenter(interactor, cicerone)

    @Provides
    fun provideMatchesInteractor(repositoryPokemons: PokemonsRepository)  : PokemonsInteractor
            = PokemonsInteractorImpl(repositoryPokemons)

}