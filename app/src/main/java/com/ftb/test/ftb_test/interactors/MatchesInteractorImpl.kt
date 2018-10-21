package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MatchesInteractorImpl(val repositoryMatches: MatchesRepository,
                            val repositoryPredictions: PredictionsRepository) : MatchesInteractor {

    override fun getMatches(): Observable<List<MatchesBase>> {
        return repositoryMatches.getMatchesFromDb().toObservable().concatWith(repositoryMatches.getMatchesFromNetwork())
    }

    override fun updateData(matches: List<MatchesBase>): Completable {
        return repositoryMatches.saveMatchesToDb(matches)
    }

    override fun updatePredictions(prediction: PredictionBase): Completable {
        return repositoryPredictions.savePredictionsToDb(prediction)
    }
}