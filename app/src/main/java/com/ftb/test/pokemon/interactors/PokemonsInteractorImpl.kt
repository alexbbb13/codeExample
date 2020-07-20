package com.ftb.test.pokemon.interactors

import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.PredictionBase
import com.ftb.test.pokemon.repositories.PokemonsRepository
import com.ftb.test.pokemon.repositories.PredictionsRepository
import io.reactivex.Completable
import io.reactivex.Observable

class PokemonsInteractorImpl(val repositoryPokemons: PokemonsRepository,
                             val repositoryPredictions: PredictionsRepository,
                             val networkLimiter: NetworkLimiter) : PokemonsInteractor {

    override fun getPokemons(): Observable<List<PokemonBase>> {
        var cache: List<PokemonBase>  //"https://pokeapi.co/api/v2/pokemon/1/" -> 1
       = emptyList()
        return repositoryPokemons.getPokemonsFromDb(1, 30).toObservable()
            .doOnNext { cache = it }
            .count()
            .flatMapObservable {
                when(it) {
                    30L -> Observable.just(cache)
                    else -> repositoryPokemons.getPokemonsFromNetwork(1, 30)
                        .doOnNext { repositoryPokemons.savePokemonsToToDb(it) }
                }
            }
//        if (networkLimiter.isMatchNetworkLimited()) {
//            return repositoryPokemons.getPokemonsFromDb(1, 30).toObservable()
//        } else {
//            return repositoryPokemons.getPokemonsFromDb(1, 30).toObservable().concatWith(repositoryPokemons.getPokemonsFromNetwork(1, 30))
//        }
    }

    override fun updateData(pokemons: List<PokemonBase>): Completable {
        return repositoryPokemons.savePokemonsToToDb(pokemons)
    }

    override fun updatePredictions(prediction: PredictionBase): Completable {
        return repositoryPredictions.savePredictionsToDb(prediction)
    }
}