package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorageImpl
import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorageImpl
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.MatchesRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MatchesModule {

    @Provides
    @Singleton
    fun getMatchesApi(retroFit: Retrofit): MatchesApi {
        return retroFit.create(MatchesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchesRepository(remoteStorage: MatchesRemoteStorage,
                                 localStorage: MatchesLocalStorage) : MatchesRepository = MatchesRepositoryImpl(remoteStorage, localStorage)

    @Provides
    @Singleton
    fun provideMatchesRemoteStorage(api: MatchesApi) : MatchesRemoteStorage = MatchesRemoteStorageImpl(api)

    @Provides
    @Singleton
    fun provideMatchesLocalStorage(db: Single<AppRoomDatabase>) : MatchesLocalStorage = MatchesLocalStorageImpl(db)
}

