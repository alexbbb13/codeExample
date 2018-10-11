package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.models.ResultBase
import io.reactivex.Observable

interface ResultsInteractor {
    fun getResults(): Observable<List<ResultBase>>
}