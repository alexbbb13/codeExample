package com.ftb.test.pokemon.interactors

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.data.models.ResultBase
import com.ftb.test.pokemon.repositories.ResultsRepository
import io.reactivex.Completable
import io.reactivex.Observable

class ResultsInteractorImpl(val repositoryResults: ResultsRepository, val networkLimiter: NetworkLimiter) : ResultsInteractor {

    override fun getResults(): Observable<List<ResultBase>> {
        if (networkLimiter.isPredictionNetworkLimited()) {
            return repositoryResults.getResultsFromDb().toObservable()
        } else {
            networkLimiter.resetPredictionNetworkLimit()
            return repositoryResults.getResultsFromDb().toObservable().concatWith(repositoryResults.getResultsFromNetwork())
        }
    }

    override fun updateData(matches: List<ResultBase>): Completable {
        return repositoryResults.saveResultsToDb(matches)
    }
}