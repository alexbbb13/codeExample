package com.ftb.test.ftb_test.data.models

import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseWithPredictionsBase
import com.ftb.test.ftb_test.data.localstorage.results.ResultsBaseDb
import com.ftb.test.ftb_test.data.localstorage.results.ResultsBaseWithPredictionsBase
import com.ftb.test.ftb_test.data.models.network.ResultNetworkDao
import com.ftb.test.ftb_test.utils.BettingMath

class ResultBase(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        val team1_points: Int,
        val team2_points: Int,
        var team1_prediction: Int,
        var team2_prediction: Int) {
    constructor(dao: ResultNetworkDao) : this(dao.team1,
            dao.team2,
            dao.team1.hashCode().xor(dao.team2.hashCode()),
            dao.team1_points,
            dao.team2_points,
            -1,
            -1) {
    }

    constructor(dao: ResultsBaseDb) : this(dao.team1,
            dao.team2,
            dao.team1.hashCode().xor(dao.team2.hashCode()),
            dao.team1points,
            dao.team2points,
            -1,
            -1) {
    }

    companion object {
        fun from(dao: ResultsBaseWithPredictionsBase): ResultBase{
            var pTeam1: Int = -1
            var pTeam2: Int = -1
            dao.predictionsList?.let {
                if(it.size > 0) {
                    pTeam1 = it.get(0).predictionTeam1
                    pTeam2 = it.get(0).predictionTeam2
                }
            }
            return ResultBase(dao.result!!.team1,
                    dao.result!!.team2,
                    BettingMath.hash(dao.result!!.team1, dao.result!!.team2),
                    dao.result!!.team1points,
                    dao.result!!.team2points,
                    pTeam1,
                    pTeam2)

        }
    }
}