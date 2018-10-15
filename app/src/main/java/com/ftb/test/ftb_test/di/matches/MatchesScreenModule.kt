package com.ftb.test.ftb_test.di.matches

import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.interactors.MatchesInteractorImpl
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.repositories.MatchesRepository
import dagger.Module
import dagger.Provides

@Module
class MatchesScreenModule {

    @Provides
    fun provideMatchesPresenter(interactor: MatchesInteractor) = MatchesPresenter(interactor)

    @Provides
    fun provideMatchesInteractor(repositoryMatches: MatchesRepository)  : MatchesInteractor
            = MatchesInteractorImpl(repositoryMatches)
}