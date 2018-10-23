package com.ftb.test.ftb_test.data.remotestorage.interfaces

import com.ftb.test.ftb_test.data.models.MatchesBase
import io.reactivex.Single

interface MatchesRemoteStorage {
    fun getMatches(): Single<List<MatchesBase>>
}