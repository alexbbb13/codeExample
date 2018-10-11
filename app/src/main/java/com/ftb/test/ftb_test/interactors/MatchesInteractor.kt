package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.models.MatchBase
import io.reactivex.Observable

interface MatchesInteractor {
    fun getMatches(): Observable<List<MatchBase>>
}