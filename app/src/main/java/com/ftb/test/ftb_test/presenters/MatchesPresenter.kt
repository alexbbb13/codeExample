package com.ftb.test.ftb_test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.PredictionBase
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.navigation.AppRouter
import com.ftb.test.ftb_test.navigation.FtbNavigator
import com.ftb.test.ftb_test.ui.dialogs.TwoButtonDialogFragment
import com.ftb.test.ftb_test.ui.matches.MatchesView
import com.ftb.test.ftb_test.utils.BettingMath
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone

@InjectViewState
class MatchesPresenter constructor(val interactor: MatchesInteractor, val cicerone: Cicerone<AppRouter>) : MvpPresenter<MatchesView>() {


    var predictionsExist = false;
    var cachedData :List <MatchesBase>? = null;


    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        load()
    }

    private fun onComplete(data: List<MatchesBase>) {
        if(cachedData == null) cachedData = data
        store(data)
        checkPredictionsExist(data)
        viewState.switchResultsButton(predictionsExist)
        update(data)
        viewState.setData(cachedData!!)
    }

    private fun checkPredictionsExist(data: List<MatchesBase>) {
        if(!predictionsExist) {
            data.forEach { match -> checkMatch(match)
            }
        }
    }

    private fun checkMatch(matchesBase: MatchesBase){
        if ((matchesBase.team1_prediction!=null && matchesBase.team1_prediction != -1)||
        (matchesBase.team2_prediction!=null && matchesBase.team2_prediction != -1)) predictionsExist = true
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun store(data: List<MatchesBase>) {
        interactor.updateData(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({})
    }

    private fun load() {
        interactor.getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    fun selectedMatch(item: MatchesBase) {
        viewState.beginMatchSelection(item.team1, item.team2, item.team1_prediction, item.team2_prediction)
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
        //cicerone.router.navigateTo(FtbNavigator.RESULTS)
    }

    fun update(data: List <MatchesBase>){
        data.forEach {
            updateCache(it)
        }
    }

    private fun updateCache(item: MatchesBase){
        cachedData!!.forEach {
            if (it.matchHash == item.matchHash) {
                if (it.team1_prediction == null) it.team1_prediction = item.team1_prediction
                if (it.team2_prediction == null) it.team2_prediction = item.team2_prediction
                if (it.team1_prediction != item.team1_prediction && item.team1_prediction != -1 && item.team1_prediction != null) it.team1_prediction = item.team1_prediction
                if (it.team2_prediction != item.team2_prediction && item.team2_prediction != -1 && item.team2_prediction != null) it.team2_prediction = item.team2_prediction
            }
        }
    }

}