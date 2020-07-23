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
import java.security.InvalidParameterException

class PokemonsInteractorImpl(val repositoryPokemons: PokemonsRepository) : PokemonsInteractor {

    val PAGE_SIZE = 30
    var currentOffset = 0
    var currentLimit = PAGE_SIZE

    override fun getPokemonsOneByOne(startPos: Int): Observable<PokemonBase> {
        return repositoryPokemons.getPokemonsFromDb(startPos + currentOffset + 1, startPos + currentOffset + currentLimit).toObservable()
            .switchMap {
                when(it.size) {
                    //Get first list of 30 items
                    0 -> repositoryPokemons.getShortPokemonListFromNetwork(startPos + currentOffset + 1, startPos + currentOffset + currentLimit )
                    PAGE_SIZE ->  Observable.just(it)
                    else -> Observable.error(InvalidParameterException("The db should return 0 or 30 items"))

                }
            }
            .flatMap { list -> Observable.fromIterable(list) }
            .switchMap { item ->
                when (item.pictureUrl.isNullOrEmpty()) {
                    true -> repositoryPokemons.getPokemonFromNetwork(item.id)
                        .doOnNext{pokemonData -> repositoryPokemons.savePokemonsToToDb(arrayListOf(pokemonData)) }
                    false -> Observable.just(item)
                }
            }
    }

    override fun getPokemons(startPos: Int): Observable<List<PokemonBase>> {
          return repositoryPokemons.getPokemonsFromDb(startPos+currentOffset+1, startPos+currentOffset+currentLimit).toObservable()
            .switchMap {
                if(it.size==PAGE_SIZE) {
                    val start = startPos+ currentOffset+1
                    val stop = startPos+currentOffset+currentLimit
                    val size = it.size
                    Log.w("doxxxtor", "Full page: got $size pokemons from db, ids from {$start} to {$stop}")
                    currentOffset += currentLimit
                    Observable.just(it)
                } else {
                    val start = startPos+currentOffset+1
                    val stop = startPos+currentOffset+currentLimit
                    val size = it.size
                    Log.w("doxxxtor", "***SHORT page: got $size pokemons from network, ids from {$start} to {$stop}")
                    //Observable.just(it)
                    repositoryPokemons.getPokemonsFromNetwork(startPos+currentOffset, currentLimit)
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