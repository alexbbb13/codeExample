package com.ftb.test.pokemon.repositories

import com.ftb.test.pokemon.data.localstorage.interfaces.PokemonsLocalStorage
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.network.PokemonFullNetworkDao
import com.ftb.test.pokemon.data.models.network.PokemonShortNetworkDao
import com.ftb.test.pokemon.data.remotestorage.interfaces.PokemonsRemoteStorage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

class PokemonsRepositoryImpl(
    private val remoteStorage: PokemonsRemoteStorage,
    private val localStorage: PokemonsLocalStorage
) : PokemonsRepository {

    override fun getPokemonsFromDb(from: Int, to: Int): Maybe<List<PokemonBase>> {
        return localStorage.getPokemons(from, to)
    }

    override fun savePokemonsToToDb(pokemons: List<PokemonBase>): Completable {
        return localStorage.replacePokemons(pokemons)
    }

    override fun getPokemonsFromNetwork(offset: Int, limit: Int): Observable<List<PokemonBase>> {
        //The API should have pokemon ids in the list, but it does not -> getting ids from url
        //The delays on the main screens are due to the fact, that display of 30 pokemons without pictures requires 1 network call,
        // but adding pictures (and other data) requires 30 network calls x 200Kb each. Consequently, it turns out to be slooooow(poke)
        return remoteStorage.getPokemonsList(offset, limit)
            .flatMap { res -> gatherPokemons(res.results)}
        }

    override fun getShortPokemonListFromNetwork(offset: Int, limit: Int): Observable<List<PokemonBase>> {
        //The API should have pokemon ids in the list, but it does not -> getting ids from url
        //The delays on the main screens are due to the fact, that display of 30 pokemons without pictures requires 1 network call,
        // but adding pictures (and other data) requires 30 network calls x 200Kb each. Consequently, it turns out to be slooooow(poke)
        return remoteStorage.getPokemonsList(offset, limit).map { pNd -> pNd.results.map{item -> dtoToData(item)}}
    }


private fun gatherPokemons(shortList: List<PokemonShortNetworkDao>): Observable<List<PokemonBase>> {
    //Initial output (names only)
    val observables = shortList.map{ remoteStorage.getPokemonForId(getPokemonIdFromUrl(it.url))}
    return shortList
        .toObservable()
        .flatMap { l -> Observable.just(dtoToData(l)) }.toList().toObservable()
        .concatWith(Observable.merge(observables).map { pNd -> dtoToData(pNd)}.toList())
}

    override fun getPokemonFromNetwork(id: Int): Observable<PokemonBase> {
        return remoteStorage.getPokemonForId(id).map { dtoToData(it) }
    }

    private fun dtoToData(dao: PokemonFullNetworkDao): PokemonBase {
        var attack = 0
        dao.stats.forEach { if (it.stat.name == "attack") attack = it.base_stat }
        var defence = 0
        dao.stats.forEach { if (it.stat.name == "defence") defence=it.base_stat }
        return PokemonBase(
            dao.id,
            dao.name,
            dao.sprites.get("front_default") ?: "",
            dao.types[0].type?.name ?: "",
            dao.height,
            dao.weight,
            attack,
            defence
        )
    }

    private fun dtoToData(pnd: PokemonShortNetworkDao): PokemonBase {
        //Almost empty pokemon (name only) from net

        return PokemonBase(
            getPokemonIdFromUrl(pnd.url),
            pnd.name,
        "",
            "",
        0,
        0,
        0,
        0
        )
    }

    private fun getPokemonIdFromUrl(url:String): Int {
        return url.substringAfter("https://pokeapi.co/api/v2/pokemon/").filter { ch->ch.isDigit() }.toInt()
    }
}