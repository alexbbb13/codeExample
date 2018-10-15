package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.models.MatchesBase
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface MatchesRepository {
    fun getMatchesFromDb(): Maybe<List<MatchesBase>>
    fun saveMatchesToDb(matches: List<MatchesBase>)
    fun getMatchesFromNetwork(): Single<List<MatchesBase>>
}