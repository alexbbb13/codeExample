package com.ftb.test.pokemon.data.remotestorage

import com.ftb.test.pokemon.data.models.network.ResultsNetworkDaoList
import io.reactivex.Single
import retrofit2.http.GET

interface ResultsApi {
    @GET("v2/5bcd90e72f00007500c85410")
    fun loadResults(
    ): Single<ResultsNetworkDaoList>
}