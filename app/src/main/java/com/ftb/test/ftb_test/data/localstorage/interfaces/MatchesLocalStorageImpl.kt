package com.ftb.test.ftb_test.data.localstorage.interfaces

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single

class MatchesLocalStorageImpl(
        private val db: Single<AppRoomDatabase>
) : MatchesLocalStorage {

    override fun getMatches(): Maybe<List<MatchesBase>> {
        return db.map(AppRoomDatabase::getMatchesDao)
                .flatMap { it.getBaseMatchess() }
                .flatMapMaybe { matches -> Maybe.just(matches.map { MatchesBase(it) }) }
    }

    override fun replaceMatches(matches: List<MatchesBase>): Completable {
        return db.map(AppRoomDatabase::getMatchesDao)
                .flatMapCompletable { Completable.fromAction { it.replaceBaseMatchess(getIterable(matches)) } }
    }

    private fun getIterable(matches: List<MatchesBase>): Iterable<MatchesBaseDb> {
        return matches.map { MatchesBaseDb(it) }
    }
}