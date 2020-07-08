package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable

interface PredictionsLocalStorage {
    fun replacePredictions(prediction: PredictionBase): Completable
}