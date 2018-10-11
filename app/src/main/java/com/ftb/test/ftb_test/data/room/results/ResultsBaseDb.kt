package com.ftb.test.ftb_test.data.room.results

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(tableName = "ResultsBase", primaryKeys = ["matchHash"])
data class ResultsBaseDb(
        val matchHash: Int,
        val team1: String,
        val team2: String,
        val team1points: Int,
        val team2points: Int)
