package com.ftb.test.ftb_test.di.app

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.PredictionsLocalStorageImpl
import com.ftb.test.ftb_test.repositories.PredictionsRepository
import com.ftb.test.ftb_test.repositories.PredictionsRepositoryImpl
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

