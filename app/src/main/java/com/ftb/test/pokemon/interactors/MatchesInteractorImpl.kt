package com.ftb.test.pokemon.interactors

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PredictionBase
import com.ftb.test.pokemon.repositories.MatchesRepository
import com.ftb.test.pokemon.repositories.PredictionsRepository
import io.reactivex.Completable
import io.reactivex.Observable

class MatchesInteractorImpl(val repositoryMatches: MatchesRepository,
                            val repositoryPredictions: PredictionsRepository,
                            val networkLimiter: NetworkLimiter) : MatchesInteractor {

    override fun getMatches(): Observable<List<MatchesBase>> {
        if (networkLimiter.isMatchNetworkLimited()) {
            return repositoryMatches.getMatchesFromDb().toObservable()
        } else {
            return repositoryMatches.getMatchesFromDb().toObservable().concatWith(repositoryMatches.getMatchesFromNetwork())
        }
    }

    override fun updateData(matches: List<MatchesBase>): Completable {
        return repositoryMatches.saveMatchesToDb(matches)
    }

    override fun updatePredictions(prediction: PredictionBase): Completable {
        return repositoryPredictions.savePredictionsToDb(prediction)
    }
}