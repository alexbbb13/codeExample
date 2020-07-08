package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.pokemon.data.localstorage.interfaces.MatchesLocalStorageImpl
import com.ftb.test.pokemon.data.remotestorage.MatchesApi
import com.ftb.test.pokemon.data.remotestorage.interfaces.MatchesRemoteStorage
import com.ftb.test.pokemon.data.remotestorage.interfaces.MatchesRemoteStorageImpl
import com.ftb.test.pokemon.repositories.MatchesRepository
import com.ftb.test.pokemon.repositories.MatchesRepositoryImpl
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

