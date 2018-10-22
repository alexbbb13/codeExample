package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.limiter.NetworkLimiter
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MatchesInteractorImpl(val repositoryMatches: MatchesRepository,
                            val repositoryPredictions: PredictionsRepository,
                            val networkLimiter: NetworkLimiter) : MatchesInteractor {

    override fun getMatches(): Observable<List<MatchesBase>> {
        if (networkLimiter.isLimited()) {
            return repositoryMatches.getMatchesFromDb().toObservable()
        } else {
            return repositoryMatches.getMatchesFromDb().toObservable().concatWith(repositoryMatches.getMatchesFromNetwork())
            networkLimiter.resetLimit()
        }
    }

    override fun updateData(matches: List<MatchesBase>): Completable {
        return repositoryMatches.saveMatchesToDb(matches)
    }

    override fun updatePredictions(prediction: PredictionBase): Completable {
        return repositoryPredictions.savePredictionsToDb(prediction)
    }
}