package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class MatchesBaseWithPredictionsBase {
        @Embedded
        var match: PokemonBaseDb? = null
        @Relation(parentColumn = "matchBaseHash", entityColumn = "predictionBaseHash", entity = PredictionsBaseDb::class)
        var predictionsList: List<PredictionsBaseDb>? = null
}