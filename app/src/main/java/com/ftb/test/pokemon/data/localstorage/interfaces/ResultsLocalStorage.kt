package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe

interface ResultsLocalStorage {
    fun getResults(): Maybe<List<ResultBase>>
    fun replaceResults(results: List<ResultBase>): Completable
}