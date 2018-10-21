package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class MatchesBaseWithPredictionsBase {
        @Embedded
        var match: MatchesBaseDb? = null
        @Relation(parentColumn = "matchBaseHash", entityColumn = "predictionBaseHash", entity = PredictionsBaseDb::class)
        var predictionsList: List<PredictionsBaseDb>? = null
}