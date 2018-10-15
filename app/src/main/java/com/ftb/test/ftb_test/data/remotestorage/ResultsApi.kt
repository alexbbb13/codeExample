package com.ftb.test.ftb_test.data.remotestorage

import com.ftb.test.ftb_test.data.localstorage.results.ResultsDao
import com.ftb.test.ftb_test.data.models.network.ResultNetworkDao
import io.reactivex.Single
import retrofit2.http.GET

interface ResultsApi {
    @GET("v1/accounts/list")
    fun loadResults(
    ): Single<List<ResultNetworkDao>>
}