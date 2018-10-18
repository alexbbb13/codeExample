package com.ftb.test.ftb_test.data.models

import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao
import com.ftb.test.ftb_test.utils.Math

class MatchesBase(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        var team1_prediction: Int,
        var team2_prediction: Int) {
    constructor(dao: MatchNetworkDao) : this(dao.team1,
            dao.team2,
            Math.hash(dao.team1, dao.team2),
            -1,
            -1)

    constructor(dao: MatchesBaseDb) : this(dao.team1,
            dao.team2,
            Math.hash(dao.team1, dao.team2),
            dao.predictionMatchTeam1,
            dao.predictionMatchTeam2)
}