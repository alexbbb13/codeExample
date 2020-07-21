package com.ftb.test.pokemon.interactors


import com.ftb.test.pokemon.data.models.PokemonBase
import io.reactivex.Observable

interface PokemonsInteractor {
    fun getPokemons(): Observable<List<PokemonBase>>
    fun getPokemonsForId(id: Int): Observable<PokemonBase>
}