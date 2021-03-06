package com.ftb.test.pokemon.data.localstorage.results

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface ResultsDao {

    @Query("SELECT * FROM ResultsBase")
    fun getBaseResults(): Single<List<ResultsBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBaseResults(item: ResultsBaseDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBaseResultss(items: Iterable<ResultsBaseDb>)

    @Transaction
    fun replaceBaseResultss(items: Iterable<ResultsBaseDb>) {
        deleteBaseResultssAll()
        insertBaseResultss(items)
    }

    @Query("SELECT * FROM ResultsBase")
    fun getResultsBaseWithPredictionsBase(): Single<List<ResultsBaseWithPredictionsBase>>

    @Query("DELETE FROM ResultsBase")
    fun deleteBaseResultssAll()
}