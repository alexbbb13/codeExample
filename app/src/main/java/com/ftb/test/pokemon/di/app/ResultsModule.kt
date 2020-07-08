package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.interfaces.ResultsLocalStorage
import com.ftb.test.pokemon.data.localstorage.interfaces.ResultsLocalStorageImpl
import com.ftb.test.pokemon.data.remotestorage.ResultsApi
import com.ftb.test.pokemon.data.remotestorage.interfaces.ResultsRemoteStorage
import com.ftb.test.pokemon.data.remotestorage.interfaces.ResultsRemoteStorageImpl
import com.ftb.test.pokemon.repositories.ResultsRepository
import com.ftb.test.pokemon.repositories.ResultsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
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

