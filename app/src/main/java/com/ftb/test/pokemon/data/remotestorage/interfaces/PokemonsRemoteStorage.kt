package com.ftb.test.pokemon.data.remotestorage.interfaces

import com.ftb.test.pokemon.data.models.MatchesBase
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.network.PokemonFullNetworkDao
import com.ftb.test.pokemon.data.models.network.PokemonsListNetworkDao
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonsRemoteStorage {
    fun getPokemonForId(id: Int): Observable<PokemonFullNetworkDao>
    fun getPokemonsList(offset: Int, limit: Int): Observable<PokemonsListNetworkDao>
}