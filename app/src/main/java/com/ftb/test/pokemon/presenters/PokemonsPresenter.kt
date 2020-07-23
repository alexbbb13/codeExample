package com.ftb.test.pokemon.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.pokemon.application.FtbApplication
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.navigation.AppRouter
import com.ftb.test.pokemon.navigation.FtbNavigator
import com.ftb.test.pokemon.ui.matches.PokemonsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import kotlin.collections.ArrayList

@InjectViewState
class PokemonsPresenter constructor(val interactor: PokemonsInteractor, val cicerone: Cicerone<AppRouter>) : MvpPresenter<PokemonsView>() {

    var cachedData :ArrayList <PokemonBase> = arrayListOf()
    var startPos:Int = 0 //position in the pokemon list 0-xxxx
    var isLoading = false;

    override fun attachView(view: PokemonsView?) {
        super.attachView(view)
        load()
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun load() {
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
        if(cachedData.isEmpty()) {
            cachedData.addAll(data)
        }
        viewState.setData(cachedData)
    }

    private fun onMoreNewDataLoaded(data: List<PokemonBase>) {
        //Caching latest loaded
        if (isLoading) {
            isLoading = false;
        }
        updateCacheAddedNew(data)
        viewState.setData(cachedData)
    }

    fun selectedMatch(item: PokemonBase) {
        FtbApplication.INSTANCE.getRouter().navigateTo(FtbNavigator.POKEMON_DETAILS, item.id)
    }

    fun updateButtonClicked() {
        startPos = 0
        cachedData.clear()
        load();
    }

    fun loadMore(findLastCompletelyVisibleItemPosition: Int) {
        if (!isLoading) {
            if (findLastCompletelyVisibleItemPosition == cachedData.size - 1) {
                //bottom of list!
                loadMoreFromServer()
                isLoading = true
            }
        }
    }

    fun updateCacheAddedNew(newData: List <PokemonBase>): Boolean{
        if(newData[0].id > cachedData.size) {
            //Insert
            //either one item in list
            //or a list of data
            cachedData.addAll(newData)
            return true
        } else {
            //Update
            newData.forEach { newPokemon -> cachedData[newPokemon.id - 1 - startPos] = newPokemon }
            return false
        }
    }
}