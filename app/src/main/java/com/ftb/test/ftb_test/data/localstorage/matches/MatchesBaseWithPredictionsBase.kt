package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Embedded

data class MatchesBaseWithPredictionsBase(
        @Embedded
        val match: MatchesDao,
        @Embedded
        val prediction: PredictionsDao?
)