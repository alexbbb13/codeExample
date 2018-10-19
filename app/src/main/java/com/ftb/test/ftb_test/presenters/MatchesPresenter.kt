package com.ftb.test.ftb_test.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.ui.dialogs.TwoButtonDialogFragment
import com.ftb.test.ftb_test.ui.matches.MatchesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException

@InjectViewState
class MatchesPresenter constructor(val interactor: MatchesInteractor) : MvpPresenter<MatchesView>() {

    lateinit var cachedData :List <MatchesBase>;

    init {
        Log.d ("TTT", "asda")
    }

    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        interactor.getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: List <MatchesBase>){
        cachedData = data;
        viewState.setData(data)
    }

    private fun onError(t: Throwable){
        t.printStackTrace()
    }

    public fun onMatchClick(match: MatchesBase){
        //viewState.openPredictionDialog(match)
    }

    public fun onPredictionCompletedClick(match: MatchesBase, score1: Int, score2: Int){
        cachedData.forEach({dataItem -> checkAndReplaceScores(dataItem, match.matchHash,score1,score2)})
        interactor.updateData(cachedData)
        viewState.setData(cachedData)
    }

    private fun checkAndReplaceScores(matchReplace: MatchesBase, matchHash: Int, score1: Int, score2: Int){
        if( matchReplace.matchHash == matchHash) {
            matchReplace.team1_prediction = score1
            matchReplace.team2_prediction = score2
        }
    }

    fun selectedMatch(item: MatchesBase) {
        viewState.beginMatchSelection(item.team1, item.team2, item.team1_prediction, item.team2_prediction)
    }

    fun onDialogChoiceClick(choice: Int, team1Name: String, team2Name: String, team1Score: Int, team2Score: Int) {
        if(choice == TwoButtonDialogFragment.SELECTED_YES) {
            throw RuntimeException("Ebtaaaa score ${team1Score} - ${team2Score}")
        }
    }
}