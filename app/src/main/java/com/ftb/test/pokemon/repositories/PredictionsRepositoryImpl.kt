package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable

class PredictionsRepositoryImpl(
        private val localStorage: PredictionsLocalStorage
) : PredictionsRepository {

      override fun savePredictionsToDb(prediction: PredictionBase): Completable {
        return localStorage.replacePredictions(prediction)
    }
}