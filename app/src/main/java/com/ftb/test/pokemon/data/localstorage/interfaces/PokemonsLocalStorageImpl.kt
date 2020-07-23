package com.ftb.test.pokemon.data.localstorage.interfaces

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.matches.PokemonBaseDb
import com.ftb.test.pokemon.data.models.PokemonBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class PokemonsLocalStorageImpl(
        private val db: Single<AppRoomDatabase>
) : PokemonsLocalStorage {

    override fun getPokemons(from: Int, to:Int): Maybe<List<PokemonBase>> {
                return db.map(AppRoomDatabase::getPokemonsDao)
                .flatMapMaybe { it.getPokemons(from, to) }
                    .map { it.map { dbToData(it) } }
    }

    private fun dbToData(db: PokemonBaseDb): PokemonBase {
        return PokemonBase(
            db.id,
            db.name,
            db.pictureUrl,
            db.type,
            db.height,
            db.weight,
            db.attack,
            db.defence
        )
    }

    override fun replacePokemons(pokemons: List<PokemonBase>): Completable {
        return db.map(AppRoomDatabase::getPokemonsDao)
                .flatMapCompletable { Completable.fromAction { it.replacePokemons(pokemons.map { PokemonBaseDb(it) }) } }
    }
}