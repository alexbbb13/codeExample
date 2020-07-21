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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone

@InjectViewState
class MatchesPresenter constructor(val interactor: PokemonsInteractor, val cicerone: Cicerone<AppRouter>) : MvpPresenter<MatchesView>() {

    var cachedData :ArrayList <PokemonBase> = arrayListOf()
    var isLoading = false

    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        load()
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun load() {
        interactor.getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNewDataLoaded, this::onError)
    }

    private fun loadMoreFromServer() {
        interactor.getPokemons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoreNewDataLoaded, this::onError)
    }

    private fun onNewDataLoaded(data: List<PokemonBase>) {
        viewState.switchResultsButton(false)
        if(cachedData.isEmpty()) {
            cachedData.addAll(data)
        }
        viewState.setData(cachedData)
    }

    private fun onMoreNewDataLoaded(data: List<PokemonBase>) {
        viewState.switchResultsButton(false)
        //Caching latest loaded
        if (isLoading) {
            //val scrollPosition = cachedData.size - 1
            //cachedData.removeAt(cachedData.size - 1)
            isLoading = false;
            //viewState.notifyItemRemoved(scrollPosition)
            //First load of empty pics
        }
        updateCacheAddedNew(data)
        viewState.setData(cachedData)
    }

    fun selectedMatch(item: PokemonBase) {
        FtbApplication.INSTANCE.getRouter().navigateTo(FtbNavigator.RESULTS, item.id)
        //viewState.beginMatchSelection(item.team1, item.team2, item.team1_prediction, item.team2_prediction)
    }

    fun resultsButtonClicked() {
        FtbApplication.INSTANCE.getRouter().replaceScreen(FtbNavigator.RESULTS)
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
        if(newData[0].id >= cachedData.size) {
            //Insert
            cachedData.addAll(newData)
            return true
        } else {
            //Update
            newData.forEach { newPokemon -> cachedData[newPokemon.id - 1] = newPokemon }
            return false
        }
    }

    private fun loadMorePokemons() {
        //cachedData.add(PokemonBaseLoading())
        //viewState.setData(cachedData)
        //viewState.notifyItemInserted(cachedData.size - 1)
        loadMoreFromServer()
    }
}