package com.ftb.test.pokemon.data.models.network

data class ResultNetworkDao(
        val team1: String,
        val team2: String,
        val team1_points: Int,
        val team2_points: Int)

data class ResultsNetworkDaoList(
        val matches: Array <ResultNetworkDao>)