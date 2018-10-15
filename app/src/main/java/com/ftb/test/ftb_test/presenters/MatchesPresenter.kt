package com.ftb.test.ftb_test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.ui.matches.MatchesView
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class MatchesPresenter constructor(val interactor: MatchesInteractor) : MvpPresenter<MatchesView>() {

    lateinit var cachedData :List <MatchesBase>;

    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        interactor.getMatches()
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
}