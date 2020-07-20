package com.ftb.test.pokemon.data.remotestorage

import com.ftb.test.pokemon.data.models.network.MatchesNetworkDaoList
import com.ftb.test.pokemon.data.models.network.PokemonFullNetworkDao
import com.ftb.test.pokemon.data.models.network.PokemonsListNetworkDao
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonsApi {
    @GET("v2/5bbb29fa3100006200148e81")
    fun loadMatches(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<PokemonsListNetworkDao>

    @GET("v2/pokemon")
    fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<PokemonsListNetworkDao>

    @GET("v2/pokemon/{id}")
    fun getPokemonForId(@Path("id") id: Int): Observable<PokemonFullNetworkDao>
}