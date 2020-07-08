package com.ftb.test.pokemon.data.localstorage.results

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsBaseDb

class ResultsBaseWithPredictionsBase {
        @Embedded
        var result: ResultsBaseDb? = null
        @Relation(parentColumn = "matchHash", entityColumn = "predictionBaseHash", entity = PredictionsBaseDb::class)
        var predictionsList: List<PredictionsBaseDb>? = null
}