package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.models.PredictionBase
import io.reactivex.Completable

interface PredictionsRepository {
    fun savePredictionsToDb(prediction: PredictionBase): Completable
}