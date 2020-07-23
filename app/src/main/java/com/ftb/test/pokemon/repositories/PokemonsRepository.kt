package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PokemonBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonsRepository {
    fun getPokemonsFromDb(from: Int, to: Int): Maybe<List<PokemonBase>>
    fun savePokemonsToToDb(matches: List<PokemonBase>): Completable
    fun getPokemonsFromNetwork(offset: Int, limit: Int): Observable<List<PokemonBase>>
    fun getPokemonFromNetwork(id: Int): Observable<PokemonBase>
    fun getShortPokemonListFromNetwork(offset: Int, limit: Int): Observable<List<PokemonBase>>
}