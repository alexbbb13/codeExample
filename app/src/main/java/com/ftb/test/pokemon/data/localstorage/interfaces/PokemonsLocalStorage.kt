package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PokemonBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface PokemonsLocalStorage {
    fun getPokemons(from: Int, to:Int): Maybe<List<PokemonBase>>
    fun replacePokemons(pokemons: List<PokemonBase>): Completable
}