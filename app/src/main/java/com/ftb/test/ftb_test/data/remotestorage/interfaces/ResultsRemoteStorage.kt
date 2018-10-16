package com.ftb.test.ftb_test.data.remotestorage.interfaces

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface ResultsRemoteStorage {
    fun getResults(): Single<List<ResultBase>>
}