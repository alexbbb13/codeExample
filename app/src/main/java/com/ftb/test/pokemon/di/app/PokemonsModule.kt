package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.interfaces.PokemonsLocalStorage
import com.ftb.test.pokemon.data.localstorage.interfaces.PokemonsLocalStorageImpl
import com.ftb.test.pokemon.data.remotestorage.PokemonsApi
import com.ftb.test.pokemon.data.remotestorage.interfaces.PokemonsRemoteStorage
import com.ftb.test.pokemon.data.remotestorage.interfaces.PokemonsRemoteStorageImpl
import com.ftb.test.pokemon.repositories.PokemonsRepository
import com.ftb.test.pokemon.repositories.PokemonsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class PokemonsModule {

    @Provides
    @Singleton
    fun getPokemonsApi(retroFit: Retrofit): PokemonsApi {
        return retroFit.create(PokemonsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchesRepository(remoteStorage: PokemonsRemoteStorage,
                                 localStorage: PokemonsLocalStorage) : PokemonsRepository = PokemonsRepositoryImpl(remoteStorage, localStorage)

    @Provides
    @Singleton
    fun provideMatchesRemoteStorage(api: PokemonsApi) : PokemonsRemoteStorage = PokemonsRemoteStorageImpl(api)

    @Provides
    @Singleton
    fun provideMatchesLocalStorage(db: Single<AppRoomDatabase>) : PokemonsLocalStorage = PokemonsLocalStorageImpl(db)
}

