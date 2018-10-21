package com.ftb.test.ftb_test.interactors


import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Observable

interface MatchesInteractor {
    fun getMatches(): Observable<List<MatchesBase>>
    fun updateData(matches: List<MatchesBase>): Completable
    fun updatePredictions(prediction: PredictionBase): Completable
}