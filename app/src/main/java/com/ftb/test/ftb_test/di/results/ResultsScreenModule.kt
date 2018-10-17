package com.ftb.test.ftb_test.di.results

import com.ftb.test.ftb_test.data.localstorage.interfaces.ResultsLocalStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.ResultsRemoteStorage
import com.ftb.test.ftb_test.interactors.ResultsInteractor
import com.ftb.test.ftb_test.interactors.ResultsInteractorImpl
import com.ftb.test.ftb_test.presenters.ResultsPresenter
import com.ftb.test.ftb_test.repositories.ResultsRepository
import com.ftb.test.ftb_test.repositories.ResultsRepositoryImpl
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