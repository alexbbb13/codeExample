package com.ftb.test.pokemon.interactors

import com.ftb.test.pokemon.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Observable

interface ResultsInteractor {
    fun getResults(): Observable<List<ResultBase>>
    fun updateData(matches: List<ResultBase>): Completable
}