package com.ftb.test.pokemon.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.pokemon.application.FtbApplication
import com.ftb.test.pokemon.data.models.PokemonBase
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
    //var cachedData :List <PokemonBase>? = null;


    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        load()
    }

    private fun onComplete(data: List<PokemonBase>) {
        //cachedData = data
        store(data)
        viewState.switchResultsButton(predictionsExist)
        viewState.setData(data)
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
                .subscribe(this::onComplete, this::onError)
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
}