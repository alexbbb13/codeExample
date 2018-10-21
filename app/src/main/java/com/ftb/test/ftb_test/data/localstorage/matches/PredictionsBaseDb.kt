package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.ftb_test.data.models.Constants
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao

@Entity(tableName = "PredictionsBase", primaryKeys = ["predictionBaseHash"])
data class PredictionsBaseDb(
        var predictionBaseHash: Int,
        var predictionTeam1: Int,
        var predictionTeam2: Int
        ) {
        constructor (p: PredictionBase) : this(p.matchHash, p.predictedScore1, p.predictedScore2)
}

