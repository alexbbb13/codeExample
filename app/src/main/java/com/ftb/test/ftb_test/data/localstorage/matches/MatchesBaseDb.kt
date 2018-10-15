package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.ftb_test.data.models.Constants
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao

@Entity(tableName = "MatchesBase", primaryKeys = ["matchHash"])
data class MatchesBaseDb(

        val team1: String,
        val team2: String,
        val matchHash: Int,
        var predictionTeam1: Int,
        var predictionTeam2: Int
        ) {
    constructor(dao: MatchNetworkDao) : this(dao.team1, dao.team2, dao.team1.hashCode().xor(dao.team2.hashCode()), Constants.NO_SCORE, Constants.NO_SCORE) {
    }

    constructor(dao: MatchesBase) : this(dao.team1, dao.team2, dao.team1.hashCode().xor(dao.team2.hashCode()), dao.team1_prediction, dao.team2_prediction)

    fun setPredictions(predictionTeam1: Int,
                       predictionTeam2: Int) {
        this.predictionTeam1 = predictionTeam1
        this.predictionTeam2 = predictionTeam2
    }
}
