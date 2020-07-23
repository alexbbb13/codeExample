package com.ftb.test.pokemon.data.models

class MatchesBase(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        var team1_prediction: Int? = null,
        var team2_prediction: Int? = null) {

}