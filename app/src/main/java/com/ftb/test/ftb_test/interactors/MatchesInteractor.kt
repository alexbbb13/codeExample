package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import io.reactivex.Observable

interface MatchesInteractor {
    fun getMatches(): Observable<List<MatchesBaseDb>>
    fun updateData(matches: List<MatchesBaseDb>)
}