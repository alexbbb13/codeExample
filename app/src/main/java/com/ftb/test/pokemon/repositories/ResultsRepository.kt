package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface ResultsRepository {
    fun getResultsFromDb(): Maybe<List<ResultBase>>
    fun saveResultsToDb(results: List<ResultBase>): Completable
    fun getResultsFromNetwork(): Single<List<ResultBase>>
}