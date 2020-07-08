package com.ftb.test.pokemon.data.models

import com.ftb.test.pokemon.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.MatchesBaseWithPredictionsBase
import com.ftb.test.pokemon.data.models.network.MatchNetworkDao
import com.ftb.test.pokemon.utils.BettingMath

class MatchesBase(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        var team1_prediction: Int? = null,
        var team2_prediction: Int? = null) {
    constructor(dao: MatchNetworkDao) : this(dao.team1,
            dao.team2,
            BettingMath.hash(dao.team1, dao.team2))

    constructor(dao: MatchesBaseDb) : this(dao.team1,
            dao.team2,
            BettingMath.hash(dao.team1, dao.team2))

    companion object {
    fun from(dao: MatchesBaseWithPredictionsBase): MatchesBase{
        var pTeam1: Int = -1
        var pTeam2: Int = -1
        dao.predictionsList?.let {
            if(it.size > 0) {
                pTeam1 = it.get(0).predictionTeam1
                pTeam2 = it.get(0).predictionTeam2
            }
        }
        return MatchesBase(dao.match!!.team1,
                dao.match!!.team2,
                BettingMath.hash(dao.match!!.team1, dao.match!!.team2),
                pTeam1,
                pTeam2)

    }
    }

}