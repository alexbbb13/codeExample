package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import io.reactivex.Observable

class MatchesInteractorImpl(val repositoryMatches: NetworkRepository):MatchesInteractor {

    override fun getMatches(): Observable<List<MatchesBaseDb>>{
        return repositoryMatches.getMatches()
    }
    override fun updateData(matches: List<MatchesBaseDb>){
        repositoryMatches.up
    }
}