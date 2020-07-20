package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MatchesDao {

    @Query("SELECT * FROM MatchesBase")
    fun getBaseMatchess(): Single<List<PokemonBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBaseMatchess(items: Iterable<PokemonBaseDb>)

    @Transaction
    fun replaceBaseMatchess(items: Iterable<PokemonBaseDb>) {
        insertBaseMatchess(items)
    }

    @Query("SELECT * FROM MatchesBase WHERE id >= :from AND id <= :to")
    fun getPokemons(from: Int, to: Int): Maybe<List<PokemonBaseDb>>

    @Query("DELETE FROM MatchesBase")
    fun deleteBaseMatchessAll()
}