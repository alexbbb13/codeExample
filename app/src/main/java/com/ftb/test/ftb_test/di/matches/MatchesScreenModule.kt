package com.ftb.test.ftb_test.di.matches

import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.interactors.MatchesInteractorImpl
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import com.ftb.test.ftb_test.repositories.ResultsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MatchesScreenModule {

    @Provides
    fun provideMatchesPresenter(interactor: MatchesInteractor) = MatchesPresenter(interactor)

    @Provides
    fun provideMatchesInteractor(repositoryMatches: MatchesRepository, repositoryP: PredictionsRepository)  : MatchesInteractor
            = MatchesInteractorImpl(repositoryMatches, repositoryP)

}