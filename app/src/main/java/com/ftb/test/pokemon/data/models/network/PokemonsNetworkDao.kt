package com.ftb.test.pokemon.data.models.network

data class MatchNetworkDao(
        val team1: String,
        val team2: String)

data class MatchesNetworkDaoList(
        val matches: Array <MatchNetworkDao>)

data class PokemonShortNetworkDao (
    val name: String,
    val url: String)

data class PokemonFullNetworkDao (
    val id: Int,
    val name: String,
    val sprites: Map<String, String>,
    val height: Int,
    val weight: Int,
    val stats: List <BasePokemonStat>,
    val types: List <PokemonType>   )

data class PokemonsListNetworkDao(
    val results: List <PokemonShortNetworkDao>
)

data class PokemonStat   (
    val name: String)

data class BasePokemonStat   (
    val base_stat: Int,
    val stat: PokemonStat)

data class PokemonType   (
    val slot : Int,
    val type: PokemonTypeName)

data class PokemonTypeName   (
    val name : String)