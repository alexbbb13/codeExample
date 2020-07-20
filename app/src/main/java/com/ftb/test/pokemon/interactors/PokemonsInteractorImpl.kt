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

    var currentOffset = 1
    var currentLimit = 30

    override fun getPokemons(): Observable<List<PokemonBase>> {
        return repositoryPokemons.getPokemonsFromDb(currentOffset, currentLimit).toObservable()
            .switchMap {
                if(it.size>3) {
                    currentOffset += currentLimit
                    Observable.just(it)
                } else {
                    repositoryPokemons.getPokemonsFromNetwork(currentOffset, currentLimit)
                        .doOnNext {
                            currentOffset += currentLimit
                        }
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