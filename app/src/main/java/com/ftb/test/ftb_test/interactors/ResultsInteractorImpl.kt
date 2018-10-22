package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.repositories.ResultsRepository
import io.reactivex.Completable
import io.reactivex.Observable

class ResultsInteractorImpl(val repositoryResults: ResultsRepository):ResultsInteractor {

    override fun getResults(): Observable<List<ResultBase>>{
        return repositoryResults.getResultsFromDb().toObservable().concatWith (repositoryResults.getResultsFromNetwork() )
    }
    override fun updateData(matches: List<ResultBase>): Completable {
        return repositoryResults.saveResultsToDb(matches)
    }
}