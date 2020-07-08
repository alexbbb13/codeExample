package com.ftb.test.pokemon.di.app

import com.ftb.test.pokemon.data.localstorage.app.AppRoomDatabase
import com.ftb.test.pokemon.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.pokemon.data.localstorage.interfaces.PredictionsLocalStorageImpl
import com.ftb.test.pokemon.repositories.PredictionsRepository
import com.ftb.test.pokemon.repositories.PredictionsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single
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

