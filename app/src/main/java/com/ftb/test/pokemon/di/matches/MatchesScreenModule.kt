package com.ftb.test.pokemon.di.matches

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.interactors.MatchesInteractor
import com.ftb.test.pokemon.interactors.MatchesInteractorImpl
import com.ftb.test.pokemon.navigation.AppRouter
import com.ftb.test.pokemon.presenters.MatchesPresenter
import com.ftb.test.pokemon.repositories.MatchesRepository
import com.ftb.test.pokemon.repositories.PredictionsRepository
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