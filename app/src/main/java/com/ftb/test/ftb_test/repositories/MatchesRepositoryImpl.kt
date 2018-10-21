package com.ftb.test.ftb_test.repositories

import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class MatchesRepositoryImpl(
        private val remoteStorage: MatchesRemoteStorage,
        private val localStorage: MatchesLocalStorage
) : MatchesRepository {

//    override fun getLoanSchedules(loanId: String, clientId: String): Observable<List<LoanSchedule>> {
//        return getLoanSchedulesFromDb(loanId).concatWith(getLoanSchedulesFromNetwork(loanId, clientId)).toObservable()
//    }
//
//    override fun getLoanSchedulesFromDb(loanId: String): Single<List<LoanSchedule>> {
//        return localStorage.getLoanSchedules(loanId)
//                .map { it.map(this::dataToModel) }
//    }
//
//    override fun getLoanSchedulesFromNetwork(loanId: String, clientId: String): Single<List<LoanSchedule>> {
//        return remoteStorage.loadLoanSchedules(loanId, clientId)
//            .flatMapCompletable { localStorage.replaceLoanSchedules(loanId, it) }
//            .andThen(getLoanSchedulesFromDb(loanId))
//    }


    override fun getMatchesFromDb(): Maybe<List<MatchesBase>> {
        return localStorage.getMatches()
    }

    override fun saveMatchesToDb(matches: List<MatchesBase>): Completable {
        return localStorage.replaceMatches(matches)
    }

    override fun getMatchesFromNetwork(): Single<List<MatchesBase>> {
        return remoteStorage.getMatches()
    }
}