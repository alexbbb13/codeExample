package com.ftb.test.ftb_test.data.localstorage.results

import android.arch.persistence.room.Entity

@Entity(tableName = "ResultsBase", primaryKeys = ["matchHash"])
data class ResultsBaseDb(
        val matchHash: Int,
        val team1: String,
        val team2: String,
        val team1points: Int,
        val team2points: Int)
