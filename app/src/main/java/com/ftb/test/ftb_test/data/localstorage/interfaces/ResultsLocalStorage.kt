package com.ftb.test.ftb_test.data.localstorage.interfaces

import com.ftb.test.ftb_test.data.models.ResultBase
import io.reactivex.Completable
import io.reactivex.Maybe

interface ResultsLocalStorage {
    fun getResults(): Maybe<List<ResultBase>>
    fun replaceResults(results: List<ResultBase>): Completable
}