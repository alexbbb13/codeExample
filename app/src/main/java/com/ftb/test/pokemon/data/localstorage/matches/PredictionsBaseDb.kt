package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.pokemon.data.models.PredictionBase

@Entity(tableName = "PredictionsBase", primaryKeys = ["predictionBaseHash"])
data class PredictionsBaseDb(
        var predictionBaseHash: Int,
        var predictionTeam1: Int,
        var predictionTeam2: Int
        ) {
        constructor (p: PredictionBase) : this(p.matchHash, p.predictedScore1, p.predictedScore2)
}

