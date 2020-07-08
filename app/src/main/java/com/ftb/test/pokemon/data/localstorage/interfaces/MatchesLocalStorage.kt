package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.models.MatchesBase
import io.reactivex.Completable
import io.reactivex.Maybe

interface MatchesLocalStorage {
    fun getMatches(): Maybe<List<MatchesBase>>
    fun replaceMatches(matches: List<MatchesBase>): Completable
}