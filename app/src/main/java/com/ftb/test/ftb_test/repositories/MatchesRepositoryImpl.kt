package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class MatchesRepositoryImpl(
        private val remoteStorage: MatchesRemoteStorage,
        private val localStorage: MatchesLocalStorage
) : MatchesRepository {

    override fun getMatchesFromDb(): Maybe<List<MatchesBase>> {
        return localStorage.getMatches()
    }

    override fun saveMatchesToDb(matches: List<MatchesBase>): Completable {
        return localStorage.replaceMatches(matches)
    }

    override fun getMatchesFromNetwork(): Single<List<MatchesBase>> {
        return remoteStorage.getMatches()
    }
}