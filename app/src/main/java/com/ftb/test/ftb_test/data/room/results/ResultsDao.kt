package com.ftb.test.ftb_test.data.room.results

import android.arch.persistence.room.*
import com.ftb.test.ftb_test.data.room.results.ResultsBaseDb
import io.reactivex.Maybe
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

    @Query("DELETE FROM ResultsBase")
    fun deleteBaseResultssAll()
}