package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsBaseDb
import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Single

class PredictionsLocalStorageImpl(
        private val db: Single<AppRoomDatabase>
) : PredictionsLocalStorage {

    override fun replacePredictions(prediction: PredictionBase): Completable {
        return db.map(AppRoomDatabase::getPredictionsDao)
                .flatMapCompletable { Completable.fromAction { it.insertBasePredictions(PredictionsBaseDb(prediction)) } }
    }
}