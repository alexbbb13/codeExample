package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface PredictionsDao {

    /*
    @Query("SELECT * FROM CardBase B LEFT JOIN CardMinPayment P ON (B.accountNumber = P.paymentAccountNumber)")
    fun getBaseCardsWithMinPayment(): Single<List<CardBaseWithMinPaymentDb>>

    @Query("SELECT * FROM MatchesBase B LEFT JOIN PredistionsBase P ON (B.hashCode = P.hashCode)")
    fun getMatchesBaseWithPredictionsBase(): Single<List<MatchesDaoWithPredictionDao>>
     */

    @Query("SELECT * FROM PredictionsBase")
    fun getBasePredictions(): Single<List<PredictionsBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasePredictions(item: PredictionsBaseDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBasePredictions(items: Iterable<PredictionsBaseDb>)


    @Query("DELETE FROM PredictionsBase")
    fun deletePredictionsAll()
}