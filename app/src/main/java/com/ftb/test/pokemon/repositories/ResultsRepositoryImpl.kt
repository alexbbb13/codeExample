package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.localstorage.interfaces.ResultsLocalStorage
import com.ftb.test.pokemon.data.models.ResultBase
import com.ftb.test.pokemon.data.remotestorage.interfaces.ResultsRemoteStorage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class ResultsRepositoryImpl(
        private val remoteStorage: ResultsRemoteStorage,
        private val localStorage: ResultsLocalStorage
) : ResultsRepository {

    override fun getResultsFromDb(): Maybe<List<ResultBase>> {
        return localStorage.getResults()
    }

    override fun saveResultsToDb(results: List<ResultBase>): Completable {
        return localStorage.replaceResults(results)
    }

    override fun getResultsFromNetwork(): Single<List<ResultBase>> {
        return remoteStorage.getResults()
    }
}