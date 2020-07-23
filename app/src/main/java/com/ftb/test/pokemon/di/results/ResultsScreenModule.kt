package com.ftb.test.pokemon.di.results

import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.interactors.PokemonsInteractorImpl
import com.ftb.test.pokemon.presenters.ResultsPresenter
import com.ftb.test.pokemon.repositories.PokemonsRepository
import dagger.Module
import dagger.Provides

@Module
class ResultsScreenModule {

    @Provides
    fun provideResultsPresenter(interactor: PokemonsInteractor) = ResultsPresenter(interactor)

    @Provides
    fun providePokemonsInteractor(repositoryPokemons: PokemonsRepository)  : PokemonsInteractor
        = PokemonsInteractorImpl(repositoryPokemons)
}