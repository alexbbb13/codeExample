package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface ResultsRepository {
    fun getResultsFromDb(): Maybe<List<ResultBase>>
    fun saveResultsToDb(results: List<ResultBase>): Completable
    fun getResultsFromNetwork(): Single<List<ResultBase>>
}