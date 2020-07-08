package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.results.ResultsBaseDb
import com.ftb.test.pokemon.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class ResultsLocalStorageImpl(
        private val db: Single<AppRoomDatabase>
) : ResultsLocalStorage {

    override fun getResults(): Maybe<List<ResultBase>> {
        return db.map(AppRoomDatabase::getResultsDao)
                .flatMap { it.getResultsBaseWithPredictionsBase() }
                .flatMapMaybe { resultsWithPredictions -> Maybe.just(resultsWithPredictions.map { ResultBase.from(it) }) }

    }

    override fun replaceResults(matches: List<ResultBase>): Completable {
        return db.map(AppRoomDatabase::getResultsDao)
                .flatMapCompletable { Completable.fromAction { it.replaceBaseResultss(getIterable(matches)) } }
    }

    private fun getIterable(matches: List<ResultBase>): Iterable<ResultsBaseDb> {
        return matches.map { ResultsBaseDb(it) }
    }
}