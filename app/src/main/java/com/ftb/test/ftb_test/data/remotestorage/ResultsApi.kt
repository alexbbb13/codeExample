package com.ftb.test.ftb_test.data.remotestorage

import com.ftb.test.ftb_test.data.localstorage.results.ResultsDao
import com.ftb.test.ftb_test.data.models.network.MatchesNetworkDaoList
import com.ftb.test.ftb_test.data.models.network.ResultNetworkDao
import com.ftb.test.ftb_test.data.models.network.ResultsNetworkDaoList
import io.reactivex.Single
import retrofit2.http.GET

interface ResultsApi {
    @GET("v2/5bcd90e72f00007500c85410")
    fun loadResults(
    ): Single<ResultsNetworkDaoList>
}