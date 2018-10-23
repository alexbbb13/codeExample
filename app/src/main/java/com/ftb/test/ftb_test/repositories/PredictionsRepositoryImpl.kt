package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.ftb_test.data.models.PredictionBase
import io.reactivex.Completable

class PredictionsRepositoryImpl(
        private val localStorage: PredictionsLocalStorage
) : PredictionsRepository {

      override fun savePredictionsToDb(prediction: PredictionBase): Completable {
        return localStorage.replacePredictions(prediction)
    }
}