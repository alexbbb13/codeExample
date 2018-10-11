package com.ftb.test.ftb_test.data.room.matches

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(tableName = "MatchesBase", primaryKeys = ["id"])
data class MatchesBaseDb(
        val id: String,
        val team1: String,
        val team2: String)
