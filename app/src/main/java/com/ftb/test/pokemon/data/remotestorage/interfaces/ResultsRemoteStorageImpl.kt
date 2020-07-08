package com.ftb.test.pokemon.data.remotestorage.interfaces

import com.ftb.test.pokemon.data.models.ResultBase
import com.ftb.test.pokemon.data.models.network.ResultsNetworkDaoList
import com.ftb.test.pokemon.data.remotestorage.ResultsApi
import io.reactivex.Single

class ResultsRemoteStorageImpl(
        private val api: ResultsApi
) : ResultsRemoteStorage {

    override fun getResults(): Single<List<ResultBase>> {
        return api.loadResults().flatMap { results -> Single.just(translate(results)) }
    }

    fun translate(list: ResultsNetworkDaoList) :List<ResultBase> {
        return list.matches.map { item -> ResultBase(item) }.toList()
    }
}