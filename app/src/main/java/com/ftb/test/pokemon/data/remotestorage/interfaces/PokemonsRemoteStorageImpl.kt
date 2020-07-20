package com.ftb.test.pokemon.data.remotestorage.interfaces

import com.ftb.test.pokemon.data.models.network.PokemonFullNetworkDao
import com.ftb.test.pokemon.data.models.network.PokemonsListNetworkDao
import com.ftb.test.pokemon.data.remotestorage.PokemonsApi
import io.reactivex.Observable


class PokemonsRemoteStorageImpl(
        private val api: PokemonsApi
) : PokemonsRemoteStorage {

    override fun getPokemonForId(id: Int): Observable<PokemonFullNetworkDao> {
        return api.getPokemonForId(id)
    }

    override fun getPokemonsList(offset: Int, limit: Int): Observable<PokemonsListNetworkDao> {
        return api.getPokemons(offset, limit)
    }
}