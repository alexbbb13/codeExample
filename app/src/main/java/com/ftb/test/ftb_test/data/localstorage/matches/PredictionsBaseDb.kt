package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.ftb_test.data.models.Constants
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao

@Entity(tableName = "PredictionsBase", primaryKeys = ["predictionBaseHash"])
class PredictionsBaseDb(
        val predictionBaseHash: Int,
        val predictionTeam1: Int,
        val predictionTeam2: Int
        )

