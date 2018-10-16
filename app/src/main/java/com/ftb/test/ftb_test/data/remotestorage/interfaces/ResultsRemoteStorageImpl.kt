package com.ftb.test.ftb_test.data.remotestorage.interfaces

import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.data.remotestorage.ResultsApi
import io.reactivex.Single

class ResultsRemoteStorageImpl(
        private val api: ResultsApi
) : ResultsRemoteStorage {

    override fun getResults(): Single<List<ResultBase>> {
        return api.loadResults().flatMap { results -> Single.just(results.map { item -> ResultBase(item) }) }
    }

}