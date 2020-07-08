package com.ftb.test.pokemon.di.results

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.interactors.ResultsInteractor
import com.ftb.test.pokemon.interactors.ResultsInteractorImpl
import com.ftb.test.pokemon.presenters.ResultsPresenter
import com.ftb.test.pokemon.repositories.ResultsRepository
import dagger.Module
import dagger.Provides

@Module
class ResultsScreenModule {

    @Provides
    fun provideResultsPresenter(interactor: ResultsInteractor) = ResultsPresenter(interactor)

    @Provides
    fun provideResultsInteractor(repositoryResults: ResultsRepository, networkLimiter: NetworkLimiter)  : ResultsInteractor
            = ResultsInteractorImpl(repositoryResults, networkLimiter)
}