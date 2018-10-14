package com.ftb.test.ftb_test.data.remotestorage

import com.ftb.test.ftb_test.data.models.MatchNetworkDao
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIInterface {

    @GET("people/?")
    fun getPeople(@Query("format") format: String): Observable<MatchNetworkDao>

    @GET
    fun getFilmData(@Url url: String, @Query("format") format: String):
}