package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.models.MatchesBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface MatchesRepository {
    fun getMatchesFromDb(): Maybe<List<MatchesBase>>
    fun saveMatchesToDb(matches: List<MatchesBase>): Completable
    fun getMatchesFromNetwork(): Single<List<MatchesBase>>
}