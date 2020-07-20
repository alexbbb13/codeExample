package com.ftb.test.pokemon.data.localstorage.matches

import android.arch.persistence.room.Entity
import com.ftb.test.pokemon.data.models.Constants
import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.network.MatchNetworkDao

@Entity(tableName = "MatchesBase", primaryKeys = ["id"])
data class PokemonBaseDb(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val type: String,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defence: Int
) {

    constructor(dao: PokemonBase) : this(
        dao.id,
        dao.name,
        dao.pictureUrl,
        dao.type,
        dao.height,
        dao.weight,
        dao.attack,
        dao.defence
    )

}
