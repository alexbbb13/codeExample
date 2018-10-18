package com.ftb.test.ftb_test.data.remotestorage

import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao
import dagger.Provides
import io.reactivex.Single
import retrofit2.http.GET

interface MatchesApi {
    @GET("v1/accounts/list")
    fun loadMatches(
    ): Single<List<MatchNetworkDao>>

}