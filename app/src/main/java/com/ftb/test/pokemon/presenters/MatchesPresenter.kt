package com.ftb.test.pokemon.presenters

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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone

@InjectViewState
class MatchesPresenter constructor(val interactor: PokemonsInteractor, val cicerone: Cicerone<AppRouter>) : MvpPresenter<MatchesView>() {


    var predictionsExist = false;
    var cachedData :ArrayList <PokemonBase> = arrayListOf()
    var isLoading = false


    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        load()
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun store(data: List<PokemonBase>) {
        interactor.updateData(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {e-> e.printStackTrace()})
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
        //cachedData = data
        store(data)
        viewState.switchResultsButton(predictionsExist)
        if(cachedData.isEmpty()) {
            cachedData.addAll(data)
            viewState.setData(cachedData)
            viewState.notifyDataSetChanged()
        } else {
            //Caching latest loaded
            if(isLoading) {
                cachedData.removeAt(cachedData.size - 1)
                val scrollPosition = cachedData.size
                isLoading = false;
                viewState.notifyItemRemoved(scrollPosition)
                if(updateCacheAddedNew(data)) {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeInserted(scrollPosition, data.size)
                } else {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeChanged(scrollPosition, data.size)
                }
            } else {
                if(updateCacheAddedNew(data)) {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeInserted(cachedData.size, data.size)
                } else {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeChanged(cachedData.size, data.size)
                }
            }

        }
        viewState.setData(data)
    }

    private fun onMoreNewDataLoaded(data: List<PokemonBase>) {
        store(data)
        viewState.switchResultsButton(predictionsExist)
            //Caching latest loaded
            if(isLoading) {
                val scrollPosition = cachedData.size - 1
                cachedData.removeAt(cachedData.size - 1)
                isLoading = false;
                viewState.notifyItemRemoved(scrollPosition)
                if(updateCacheAddedNew(data)) {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeInserted(scrollPosition, data.size)
                } else {
                    viewState.setData(cachedData)
                    viewState.notifyItemRangeChanged(scrollPosition, data.size)
                }
            } else {
              //не может быть
//                if(updateCacheAddedNew(data)) {
//                    viewState.setData(cachedData)
//                    viewState.notifyItemRangeInserted(cachedData.size, data.size)
//                } else {
//                    viewState.setData(cachedData)
//                    viewState.notifyItemRangeChanged(cachedData.size, data.size)
//                }

        }
 //       viewState.setData(data)
    }

    fun selectedMatch(item: PokemonBase) {
        //viewState.beginMatchSelection(item.team1, item.team2, item.team1_prediction, item.team2_prediction)
    }

    fun onDialogChoiceClick(choice: Int, team1Name: String, team2Name: String, team1Score: Int, team2Score: Int) {
        if (choice == TwoButtonDialogFragment.SELECTED_YES) {
            val matchHash = BettingMath.hash(team1Name, team2Name)
            val prediction = PredictionBase(matchHash, team1Score, team2Score)
            interactor.updatePredictions(prediction)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        load()
                    })
        }
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
        if(newData[0].id > cachedData.size) {
            //Insert
            cachedData.addAll(newData)
            return true
        } else {
            //Update
            newData.forEach { newPokemon -> cachedData[newPokemon.id] = newPokemon }
            return false
        }
    }

    private fun loadMorePokemons() {
        cachedData.add(PokemonBaseLoading())
        viewState.setData(cachedData)
        viewState.notifyItemInserted(cachedData.size - 1)
        loadMoreFromServer()
        //recyclerViewAdapter.notifyItemInserted(rowsArrayList.size() - 1)

//
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            rowsArrayList.remove(rowsArrayList.size() - 1)
//            val scrollPosition = rowsArrayList.size()
//            recyclerViewAdapter.notifyItemRemoved(scrollPosition)
//            var currentSize = scrollPosition
//            val nextLimit = currentSize + 10
//
//            while (currentSize - 1 < nextLimit) {
//                rowsArrayList.add("Item $currentSize")
//                currentSize++
//            }
//
//            recyclerViewAdapter.notifyDataSetChanged()
//            isLoading = false
//        }, 2000)


    }
}