package com.ftb.test.ftb_test.interactors

import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.repositories.MatchesRepository
import io.reactivex.Observable

class MatchesInteractorImpl(val repositoryMatches: MatchesRepository):MatchesInteractor {

    override fun getMatches(): Observable<List<MatchesBase>>{
        return repositoryMatches.getMatchesFromDb().toObservable().concatWith (repositoryMatches.getMatchesFromNetwork() )
    }
    override fun updateData(matches: List<MatchesBase>){
        repositoryMatches.saveMatchesToDb(matches)
    }
}