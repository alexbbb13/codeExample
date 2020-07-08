package com.ftb.test.pokemon.data.remotestorage

import com.ftb.test.pokemon.data.models.network.MatchesNetworkDaoList
import io.reactivex.Single
import retrofit2.http.GET

interface MatchesApi {
    @GET("v2/5bbb29fa3100006200148e81")
    fun loadMatches(
    ): Single<MatchesNetworkDaoList>

}