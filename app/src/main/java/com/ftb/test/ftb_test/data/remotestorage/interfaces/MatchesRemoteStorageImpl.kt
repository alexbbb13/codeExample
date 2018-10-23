package com.ftb.test.ftb_test.data.remotestorage.interfaces

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.network.MatchesNetworkDaoList
import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import io.reactivex.Single

class MatchesRemoteStorageImpl(
        private val api: MatchesApi
) : MatchesRemoteStorage {

    override fun getMatches(): Single<List<MatchesBase>> {
        return api.loadMatches().flatMap { matches -> Single.just(translate(matches)) }
    }

    fun translate(list: MatchesNetworkDaoList) :List<MatchesBase> {
        return list.matches.map { item -> MatchesBase(item) }.toList()
    }
}