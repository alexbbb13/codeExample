package com.ftb.test.ftb_test.di.results

import com.ftb.test.ftb_test.interactors.ResultsInteractor
import dagger.Module
import dagger.Provides

@Module
class ResultsScreenModule {

    @Provides
    fun provideResultsPresenter(interactor: ResultsInteractor) = ResultsPresenter(interactor)

    @Provides
    fun provideResultsInteractor(repositoryResults: ResultsRepository)  : ResultsInteractor
            = ResultsInteractorImpl(repositoryResults)
}