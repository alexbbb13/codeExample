package com.ftb.test.ftb_test.data.localstorage.interfaces

import com.ftb.test.ftb_test.data.models.MatchesBase
import io.reactivex.Completable
import io.reactivex.Maybe

interface MatchesLocalStorage {
    fun getMatches(): Maybe<List<MatchesBase>>
    fun replaceMatches(matches: List<MatchesBase>): Completable
}