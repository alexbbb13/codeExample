package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable

interface PredictionsRepository {
    fun savePredictionsToDb(prediction: PredictionBase): Completable
}