package com.ftb.test.ftb_test.data.localstorage.interfaces

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Maybe

interface PredictionsLocalStorage {
    fun replacePredictions(prediction: PredictionBase): Completable
}