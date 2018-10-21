package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorageImpl
import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorageImpl
import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import com.ftb.test.ftb_test.data.remotestorage.ResultsApi
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorageImpl
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.MatchesRepositoryImpl
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class PredictionsModule {

    @Provides
    @Singleton
    fun providePredictionsRepository(
            localStorage: PredictionsLocalStorage): PredictionsRepository = PredictionsRepositoryImpl(localStorage)

    @Provides
    @Singleton
    fun providePredictionsLocalStorage(db: Single<AppRoomDatabase>): PredictionsLocalStorage = PredictionsLocalStorageImpl(db)
}

