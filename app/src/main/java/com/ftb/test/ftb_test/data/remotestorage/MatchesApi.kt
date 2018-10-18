package com.ftb.test.ftb_test.data.remotestorage

import com.ftb.test.ftb_test.data.models.network.MatchNetworkDao
import com.ftb.test.ftb_test.data.models.network.MatchesNetworkDaoList
import dagger.Provides
import io.reactivex.Single
import retrofit2.http.GET

interface MatchesApi {
    @GET("v2/5bbb29fa3100006200148e81")
    fun loadMatches(
    ): Single<MatchesNetworkDaoList>

}