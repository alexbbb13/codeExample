package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorageImpl
import com.ftb.test.ftb_test.data.localstorage.interfaces.ResultsLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.ResultsLocalStorageImpl
import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import com.ftb.test.ftb_test.data.remotestorage.ResultsApi
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorageImpl
import com.ftb.test.ftb_test.data.remotestorage.interfaces.ResultsRemoteStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.ResultsRemoteStorageImpl
import com.ftb.test.ftb_test.repositories.ResultsRepository
import com.ftb.test.ftb_test.repositories.ResultsRepositoryImpl
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
class ResultsModule {

    @Provides
    @Singleton
    fun getResultsApi(retroFit: Retrofit): ResultsApi {
        return retroFit.create(ResultsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideResultsRepository(remoteStorage: ResultsRemoteStorage,
                                 localStorage: ResultsLocalStorage): ResultsRepository = ResultsRepositoryImpl(remoteStorage, localStorage)

    @Provides
    @Singleton
    fun provideResultsRemoteStorage(api: ResultsApi) : ResultsRemoteStorage = ResultsRemoteStorageImpl(api)

    @Provides
    @Singleton
    fun provideResultsLocalStorage(db: Single<AppRoomDatabase>) : ResultsLocalStorage = ResultsLocalStorageImpl(db)
}

