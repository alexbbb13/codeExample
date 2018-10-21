package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class PredictionsRepositoryImpl(
        private val localStorage: PredictionsLocalStorage
) : PredictionsRepository {

      override fun savePredictionsToDb(prediction: PredictionBase): Completable {
        return localStorage.replacePredictions(prediction)
    }
}