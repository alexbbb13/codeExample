package com.ftb.test.pokemon.data.remotestorage.interfaces

import com.ftb.test.pokemon.data.models.MatchesBase
import io.reactivex.Single

interface MatchesRemoteStorage {
    fun getMatches(): Single<List<MatchesBase>>
}