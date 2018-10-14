package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface MatchesDao {

    @Query("SELECT * FROM MatchesBase")
    fun getBaseMatchess(): Single<List<MatchesBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBaseMatches(item: MatchesBaseDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBaseMatchess(items: Iterable<MatchesBaseDb>)

    @Transaction
    fun replaceBaseMatchess(items: Iterable<MatchesBaseDb>) {
        deleteBaseMatchessAll()
        insertBaseMatchess(items)
    }

    @Query("DELETE FROM MatchesBase")
    fun deleteBaseMatchessAll()
}