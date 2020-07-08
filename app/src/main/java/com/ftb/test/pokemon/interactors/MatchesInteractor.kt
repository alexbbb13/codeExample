package com.ftb.test.pokemon.interactors


import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Observable

interface MatchesInteractor {
    fun getMatches(): Observable<List<MatchesBase>>
    fun updateData(matches: List<MatchesBase>): Completable
    fun updatePredictions(prediction: PredictionBase): Completable
}