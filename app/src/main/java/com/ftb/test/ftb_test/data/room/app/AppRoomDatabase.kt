package com.ftb.test.ftb_test.data.room.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ftb.test.ftb_test.data.room.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.room.matches.MatchesDao
import com.ftb.test.ftb_test.data.room.results.ResultsBaseDb
import com.ftb.test.ftb_test.data.room.results.ResultsDao

@Database(entities = [
    MatchesBaseDb::class,
    ResultsBaseDb::class
], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getMatchesDao(): MatchesDao
    abstract fun getresultsDao(): ResultsDao
}