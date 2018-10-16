package com.ftb.test.ftb_test.data.models

import com.ftb.test.ftb_test.data.localstorage.results.ResultsBaseDb
import com.ftb.test.ftb_test.data.models.network.ResultNetworkDao

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

    fun setPrediction(base: MatchesBase){
        team1_prediction = base.team1_prediction
        team2_prediction = base.team2_prediction
    }
}