package com.ftb.test.ftb_test.di.matches

import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MatchesModule {

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): MatchesApi = retrofit.create(MatchesApi::class.java)

//    @Provides
//    @Singleton
//    fun provideCardRepository(cardRemoteStorage: CardRemoteStorage,
//                              cardLocalStorage: CardLocalStorage): CardRepository =
//        CardRepositoryImpl(cardRemoteStorage, cardLocalStorage)
//
//    @Provides
//    @Singleton
//    fun provideCardStatementRepository(
//        cardRemoteStorage: CardStatementRemoteStorage,
//        cardLocalStorage: CardStatementLocalStorage
//    ): CardStatementRepository {
//        return CardStatementRepositoryImpl(
//            cardRemoteStorage,
//            cardLocalStorage
//        )
//    }
//
//    @Provides
//    @Singleton
//    fun provideCardNetworkStorage(service: CardApi, halvaApi: RetrofitApi, @RemoteScheduler scheduler: Scheduler): CardRemoteStorage =
//        CardRetrofitStorage(service, halvaApi, scheduler)
//
//    @Provides
//    @Singleton
//    fun provideCardPersistStorage(room: Single<AppRoomDatabase>, @LocalScheduler scheduler: Scheduler): CardLocalStorage = CardRoomStorage(room, scheduler)
//
//    @Provides
//    @Singleton
//    fun provideCardStatementNetworkStorage(service: CardApi, @RemoteScheduler scheduler: Scheduler): CardStatementRemoteStorage =
//        CardStatementRetrofitStorage(service, scheduler)
//
//    @Provides
//    @Singleton
//    fun provideCardStatementPersistStorage(room: Single<AppRoomDatabase>, @LocalScheduler scheduler: Scheduler): CardStatementLocalStorage =
//        CardStatementRoomStorage(room, scheduler)
}