package com.ftb.test.ftb_test.data.localstorage.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesDao
import com.ftb.test.ftb_test.data.localstorage.results.ResultsBaseDb
import com.ftb.test.ftb_test.data.localstorage.results.ResultsDao

@Database(entities = [
    MatchesBaseDb::class,
    ResultsBaseDb::class
], version = 2)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getMatchesDao(): MatchesDao
    abstract fun getResultsDao(): ResultsDao
}