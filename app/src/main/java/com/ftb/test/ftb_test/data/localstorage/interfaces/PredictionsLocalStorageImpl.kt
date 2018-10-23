package com.ftb.test.ftb_test.data.localstorage.interfaces

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.matches.PredictionsBaseDb
import com.ftb.test.ftb_test.data.models.PredictionBase
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