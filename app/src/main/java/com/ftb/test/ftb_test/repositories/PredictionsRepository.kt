package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface PredictionsRepository {
    fun savePredictionsToDb(prediction: PredictionBase): Completable
}