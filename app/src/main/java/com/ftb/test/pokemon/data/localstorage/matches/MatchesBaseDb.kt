package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.pokemon.data.models.Constants
import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.network.MatchNetworkDao

@Entity(tableName = "MatchesBase", primaryKeys = ["matchBaseHash"])
data class MatchesBaseDb(

        val team1: String,
        val team2: String,
        val matchBaseHash: Int,
        var predictionMatchTeam1: Int,
        var predictionMatchTeam2: Int
        ) {
    constructor(dao: MatchNetworkDao) : this(dao.team1, dao.team2, dao.team1.hashCode().xor(dao.team2.hashCode()), Constants.NO_SCORE, Constants.NO_SCORE) {
    }

    constructor(dao: MatchesBase) : this(dao.team1, dao.team2, dao.team1.hashCode().xor(dao.team2.hashCode()), Constants.NO_SCORE, Constants.NO_SCORE)

}
