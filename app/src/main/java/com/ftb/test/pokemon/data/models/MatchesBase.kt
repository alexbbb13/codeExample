package com.ftb.test.pokemon.data.models

import com.ftb.test.pokemon.data.localstorage.matches.PokemonBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.MatchesBaseWithPredictionsBase
import com.ftb.test.pokemon.data.models.network.MatchNetworkDao
import com.ftb.test.pokemon.utils.BettingMath

class MatchesBase(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        var team1_prediction: Int? = null,
        var team2_prediction: Int? = null) {

}