package com.ftb.test.ftb_test.di.matches

import com.ftb.test.ftb_test.data.localstorage.app.AppRoomDatabase
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorage
import com.ftb.test.ftb_test.data.localstorage.interfaces.MatchesLocalStorageImpl
import com.ftb.test.ftb_test.data.remotestorage.MatchesApi
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorage
import com.ftb.test.ftb_test.data.remotestorage.interfaces.MatchesRemoteStorageImpl
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.interactors.MatchesInteractorImpl
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.repositories.MatchesRepository
import com.ftb.test.ftb_test.repositories.MatchesRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class MatchesScreenModule {

    @Provides
    fun provideMatchesRepository(remoteStorage: MatchesRemoteStorage,
                                 localStorage: MatchesLocalStorage) : MatchesRepository = MatchesRepositoryImpl(remoteStorage, localStorage)

    @Provides
    fun provideMatchesRemoteStorage(api: MatchesApi) : MatchesRemoteStorage = MatchesRemoteStorageImpl(api)

    @Provides
    fun provideMatchesLocalStorage(db: Single<AppRoomDatabase>) : MatchesLocalStorage = MatchesLocalStorageImpl(db)

    @Provides
    fun provideMatchesPresenter(interactor: MatchesInteractor): MatchesPresenter = MatchesPresenter(interactor)

    @Provides
    fun provideMatchesInteractor(repositoryMatches: MatchesRepository)  : MatchesInteractor
            = MatchesInteractorImpl(repositoryMatches)
}