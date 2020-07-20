package com.ftb.test.pokemon.interactors


import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.PredictionBase
import io.reactivex.Completable
import io.reactivex.Observable

interface PokemonsInteractor {
    fun getPokemons(): Observable<List<PokemonBase>>
    fun updateData(matches: List<PokemonBase>): Completable
    fun updatePredictions(prediction: PredictionBase): Completable
}