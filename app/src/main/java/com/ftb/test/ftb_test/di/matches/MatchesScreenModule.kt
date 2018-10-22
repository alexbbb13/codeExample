package com.ftb.test.ftb_test.di.matches

import com.ftb.test.ftb_test.data.limiter.NetworkLimiter
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.interactors.MatchesInteractorImpl
import com.ftb.test.ftb_test.navigation.AppRouter
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone

@Module
class MatchesScreenModule {

    @Provides
    fun provideMatchesPresenter(interactor: MatchesInteractor, cicerone: Cicerone<AppRouter>) = MatchesPresenter(interactor, cicerone)

    @Provides
    fun provideMatchesInteractor(repositoryMatches: MatchesRepository, repositoryPredictions: PredictionsRepository, networkLimiter: NetworkLimiter)  : MatchesInteractor
            = MatchesInteractorImpl(repositoryMatches, repositoryPredictions, networkLimiter)

}