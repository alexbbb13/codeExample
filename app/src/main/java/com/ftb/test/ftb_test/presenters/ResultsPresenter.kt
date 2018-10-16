package com.ftb.test.ftb_test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.interactors.ResultsInteractor
import com.ftb.test.ftb_test.ui.results.ResultsView
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class ResultsPresenter constructor(val interactor: ResultsInteractor) : MvpPresenter<ResultsView>() {

    lateinit var cachedData :List <ResultBase>;

    override fun attachView(view: ResultsView?) {
        super.attachView(view)
        interactor.getResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: List <ResultBase>){
        cachedData = data;
        viewState.setData(data)
    }

    private fun onError(t: Throwable){
        t.printStackTrace()
    }

    public fun onMatchClick(match: ResultBase){
        //viewState.openPredictionDialog(match)
    }

//    public fun onPredictionCompletedClick(match: ResultBase, score1: Int, score2: Int){
//        cachedData.forEach({dataItem -> checkAndReplaceScores(dataItem, match.matchHash,score1,score2)})
//        interactor.updateData(cachedData)
//        viewState.setData(cachedData)
//    }
//
//    private fun checkAndReplaceScores(matchReplace: ResultBase, matchHash: Int, score1: Int, score2: Int){
//        if( matchReplace.matchHash == matchHash) {
//            matchReplace.team1_prediction = score1
//            matchReplace.team2_prediction = score2
//        }
//    }
}