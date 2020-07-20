package com.ftb.test.pokemon.data.models

open class PokemonBase(
        val id: Int,
        val name: String,
        val pictureUrl: String,
        val type: String,
        val height: Int,
        val weight: Int,
        val attack: Int,
        val defence: Int)
