package com.ftb.test.pokemon.data.remotestorage.interfaces

import com.ftb.test.pokemon.data.models.ResultBase
import io.reactivex.Single

interface ResultsRemoteStorage {
    fun getResults(): Single<List<ResultBase>>
}