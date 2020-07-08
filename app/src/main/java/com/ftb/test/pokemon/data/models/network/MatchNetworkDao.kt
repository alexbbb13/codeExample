package com.ftb.test.pokemon.data.models.network

data class MatchNetworkDao(
        val team1: String,
        val team2: String)

data class MatchesNetworkDaoList(
        val matches: Array <MatchNetworkDao>)