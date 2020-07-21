package com.ftb.test.pokemon.interactors

import android.util.Log
import com.ftb.test.pokemon.data.limiter.NetworkLimiter
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.PredictionBase
import com.ftb.test.pokemon.repositories.PokemonsRepository
import com.ftb.test.pokemon.repositories.PredictionsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PokemonsInteractorImpl(val repositoryPokemons: PokemonsRepository) : PokemonsInteractor {

    val PAGE_SIZE = 30
    var currentOffset = 0
    var currentLimit = PAGE_SIZE


    override fun getPokemons(): Observable<List<PokemonBase>> {
        return repositoryPokemons.getPokemonsFromDb(currentOffset+1, currentOffset+currentLimit).toObservable()
            .switchMap {
                if(it.size == PAGE_SIZE) {
                    val start = currentOffset+1
                    val stop = currentOffset+currentLimit
                    val size = it.size
                    Log.w("doxxxtor", "Full page: got $size pokemons from db, ids from {$start} to {$stop}")
                    currentOffset += currentLimit
                    Observable.just(it)
                } else {
                    val start = currentOffset+1
                    val stop = currentOffset+currentLimit
                    val size = it.size
                    Log.w("doxxxtor", "***SHORT page: got $size pokemons from network, ids from {$start} to {$stop}")
                    currentOffset += currentLimit
                    //Observable.just(it)
                    repositoryPokemons.getPokemonsFromNetwork(currentOffset, currentLimit)
                        .switchMap{
                            if(it.get(0).pictureUrl.isNullOrEmpty().not()) {
                                currentOffset += currentLimit
                                repositoryPokemons.savePokemonsToToDb(it).andThen(Observable.just(it))
                            } else {
                                Observable.just(it)
                            }
                        }
                }
            }
    }

    override fun getPokemonsForId(id: Int): Observable<PokemonBase> {
        return repositoryPokemons.getPokemonsFromDb(id, id).toObservable()
            .switchMap { list ->
                when(list.size) {
                    0 -> repositoryPokemons.getPokemonFromNetwork(id).flatMap {
                        repositoryPokemons.savePokemonsToToDb(arrayListOf(it)).andThen(Observable.just(it))
                        }
                    else -> Observable.just(list[0])
                }
            }.subscribeOn(Schedulers.io())
    }
}