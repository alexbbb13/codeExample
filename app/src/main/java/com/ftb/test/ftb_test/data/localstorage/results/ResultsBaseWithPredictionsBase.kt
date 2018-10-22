package com.ftb.test.ftb_test.data.localstorage.results

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.ftb.test.ftb_test.data.localstorage.matches.PredictionsBaseDb

class ResultsBaseWithPredictionsBase {
        @Embedded
        var result: ResultsBaseDb? = null
        @Relation(parentColumn = "matchHash", entityColumn = "predictionBaseHash", entity = PredictionsBaseDb::class)
        var predictionsList: List<PredictionsBaseDb>? = null
}