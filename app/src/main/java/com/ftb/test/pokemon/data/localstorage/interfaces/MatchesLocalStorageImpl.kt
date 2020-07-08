package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.pokemon.data.models.MatchesBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class MatchesLocalStorageImpl(
        private val db: Single<AppRoomDatabase>
) : MatchesLocalStorage {

    override fun getMatches(): Maybe<List<MatchesBase>> {
                return db.map(AppRoomDatabase::getMatchesDao)
                .flatMap { it.getMatchesBaseWithPredictionsBase() }
                .flatMapMaybe { matchesWithPredictions -> Maybe.just(matchesWithPredictions.map { MatchesBase.from(it) }) }
    }

    override fun replaceMatches(matches: List<MatchesBase>): Completable {
        return db.map(AppRoomDatabase::getMatchesDao)
                .flatMapCompletable { Completable.fromAction { it.replaceBaseMatchess(getIterable(matches)) } }
    }

    private fun getIterable(matches: List<MatchesBase>): Iterable<MatchesBaseDb> {
        return matches.map { MatchesBaseDb(it) }
    }
}