package com.ftb.test.pokemon.data.localstorage.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ftb.test.pokemon.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.MatchesDao
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsBaseDb
import com.ftb.test.pokemon.data.localstorage.matches.PredictionsDao
import com.ftb.test.pokemon.data.localstorage.results.ResultsBaseDb
import com.ftb.test.pokemon.data.localstorage.results.ResultsDao

@Database(entities = [
    MatchesBaseDb::class,
    ResultsBaseDb::class,
    PredictionsBaseDb::class
], version = 3)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getMatchesDao(): MatchesDao
    abstract fun getResultsDao(): ResultsDao
    abstract fun getPredictionsDao(): PredictionsDao
}