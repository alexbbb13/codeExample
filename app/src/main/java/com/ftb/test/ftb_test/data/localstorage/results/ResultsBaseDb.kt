package com.ftb.test.ftb_test.data.localstorage.results

import android.arch.persistence.room.Entity
import com.ftb.test.ftb_test.data.models.ResultBase

@Entity(tableName = "ResultsBase", primaryKeys = ["matchHash"])
data class ResultsBaseDb(
        val team1: String,
        val team2: String,
        val matchHash: Int,
        val team1points: Int,
        val team2points: Int,
        val team1prediction: Int,
        val team2prediction: Int) {

    constructor(dao: ResultBase) : this(dao.team1, dao.team2, dao.team1.hashCode().xor(dao.team2.hashCode()),dao.team1_points, dao.team2_points, dao.team1_prediction, dao.team2_prediction)
}