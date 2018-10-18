package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface MatchesDao {

    /*
    @Query("SELECT * FROM CardBase B LEFT JOIN CardMinPayment P ON (B.accountNumber = P.paymentAccountNumber)")
    fun getBaseCardsWithMinPayment(): Single<List<CardBaseWithMinPaymentDb>>

    @Query("SELECT * FROM MatchesBase B LEFT JOIN PredistionsBase P ON (B.hashCode = P.hashCode)")
    fun getMatchesBaseWithPredictionsBase(): Single<List<MatchesDaoWithPredictionDao>>
     */

    @Query("SELECT * FROM MatchesBase")
    fun getBaseMatchess(): Single<List<MatchesBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBaseMatches(item: MatchesBaseDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBaseMatchess(items: Iterable<MatchesBaseDb>)

    @Transaction
    fun replaceBaseMatchess(items: Iterable<MatchesBaseDb>) {
        deleteBaseMatchessAll()
        insertBaseMatchess(items)
    }

    @Query("SELECT * FROM MatchesBase B LEFT JOIN PredictionsBase P ON (B.matchHash = P.matchHash)")
    fun getMatchesBaseWithPredictionsBase(): Single<List<MatchesBaseWithPredictionsBase>>


    @Query("DELETE FROM MatchesBase")
    fun deleteBaseMatchessAll()
}