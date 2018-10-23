package com.ftb.test.ftb_test.data.localstorage.matches

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface PredictionsDao {

    @Query("SELECT * FROM PredictionsBase")
    fun getBasePredictions(): Single<List<PredictionsBaseDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasePredictions(item: PredictionsBaseDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBasePredictions(items: Iterable<PredictionsBaseDb>)


    @Query("DELETE FROM PredictionsBase")
    fun deletePredictionsAll()
}