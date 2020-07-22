package com.ftb.test.pokemon.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.pokemon.application.FtbApplication
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.PokemonBaseLoading
import com.ftb.test.pokemon.data.models.PredictionBase
import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.navigation.AppRouter
import com.ftb.test.pokemon.navigation.FtbNavigator
import com.ftb.test.pokemon.ui.dialogs.TwoButtonDialogFragment
import com.ftb.test.pokemon.ui.matches.MatchesView
import com.ftb.test.pokemon.utils.BettingMath
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class MatchesPresenter constructor(val interactor: PokemonsInteractor, val cicerone: Cicerone<AppRouter>) : MvpPresenter<MatchesView>() {

    var cachedData :ArrayList <PokemonBase> = arrayListOf()
    var startPos:Int = 0 //position in the pokemon list 0-xxxx
    var isLoading = false;

    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        load()
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun load() {
//        Observable.range(0, 30).flatMap { interactor.getPokemonsForId(it) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(this::onPokemonLoaded, this::onError)
        interactor.getPokemons(startPos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNewDataLoaded, this::onError)
    }

    private fun loadMoreFromServer() {
        interactor.getPokemons(startPos)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoreNewDataLoaded, this::onError)
    }

    private fun onNewDataLoaded(data: List<PokemonBase>) {
        viewState.switchResultsButton(true)
        if(cachedData.isEmpty()) {
            cachedData.addAll(data)
        }
        viewState.setData(cachedData)
    }

    private fun onMoreNewDataLoaded(data: List<PokemonBase>) {
        viewState.switchResultsButton(true)
        //Caching latest loaded
        if (isLoading) {
            isLoading = false;
        }
        updateCacheAddedNew(data)
        viewState.setData(cachedData)
    }

    private fun onPokemonLoaded(data: PokemonBase) {
        viewState.switchResultsButton(true)
        cachedData[data.id] = data
        //Caching latest loaded
        if (isLoading) {
            isLoading = false;
        }
        cachedData[data.id-1]=data
        //updateCacheAddedNew(cachedData)
        viewState.setData(cachedData)
    }

    fun selectedMatch(item: PokemonBase) {
        FtbApplication.INSTANCE.getRouter().navigateTo(FtbNavigator.RESULTS, item.id)
    }

    fun updateButtonClicked() {
        startPos = 4
        cachedData.clear()
        load();
    }

    fun loadMore(findLastCompletelyVisibleItemPosition: Int) {
        if (!isLoading) {
            if (findLastCompletelyVisibleItemPosition == cachedData.size - 1) {
                //bottom of list!
                loadMorePokemons()
                isLoading = true
            }
        }
    }

    fun updateCacheAddedNew(newData: List <PokemonBase>): Boolean{
        if(newData[0].id >= cachedData.size+startPos) {
            //Insert
            cachedData.addAll(newData)
            return true
        } else {
            //Update
            newData.forEach { newPokemon -> cachedData[newPokemon.id - 1 - startPos] = newPokemon }
            return false
        }
    }

    private fun loadMorePokemons() {
        loadMoreFromServer()
    }
}