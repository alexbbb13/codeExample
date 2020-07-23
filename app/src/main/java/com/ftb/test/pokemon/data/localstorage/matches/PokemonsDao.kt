package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface PokemonsDao {

    @Query("SELECT * FROM PokemonsBase")
    fun getPokemons(): Single<List<PokemonBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(items: Iterable<PokemonBaseDb>)

    @Transaction
    fun replacePokemons(items: Iterable<PokemonBaseDb>) {
        insertPokemons(items)
    }

    @Query("SELECT * FROM PokemonsBase WHERE id >= :from AND id <= :to")
    fun getPokemons(from: Int, to: Int): Maybe<List<PokemonBaseDb>>

    @Query("DELETE FROM PokemonsBase")
    fun deleteBaseMatchessAll()
}