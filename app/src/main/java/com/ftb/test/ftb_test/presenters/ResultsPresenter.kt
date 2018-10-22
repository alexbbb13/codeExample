package com.ftb.test.ftb_test.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.interactors.ResultsInteractor
import com.ftb.test.ftb_test.navigation.FtbNavigator
import com.ftb.test.ftb_test.ui.matches.MatchesDiffCallback
import com.ftb.test.ftb_test.ui.results.ResultsView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ResultsPresenter constructor(val interactor: ResultsInteractor) : MvpPresenter<ResultsView>() {

    var cachedData :List <ResultBase>? = null;

    override fun attachView(view: ResultsView?) {
        super.attachView(view)
        interactor.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: List <ResultBase>){
        if (cachedData == null) cachedData = data;
        store(data)
        update(data)
        viewState.setData(cachedData!!)
    }

    private fun onError(t: Throwable){
        t.printStackTrace()
    }

    private fun store(data: List<ResultBase>) {
        interactor.updateData(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({})
    }

    fun returnButtonClicked() {
        FtbApplication.INSTANCE.getRouter().replaceScreen(FtbNavigator.MATCHES)
    }

    fun update(data: List <ResultBase>){
        data.forEach({
            updateCache(it)
        })
    }

    private fun updateCache(item: ResultBase){
        cachedData!!.forEach({
            if (it.matchHash == item.matchHash) {
                if (it.team1_prediction == null) it.team1_prediction = item.team1_prediction
                if (it.team2_prediction == null) it.team2_prediction = item.team2_prediction
                if (it.team1_prediction != item.team1_prediction && item.team1_prediction != -1 && item.team1_prediction != null) it.team1_prediction = item.team1_prediction
                if (it.team2_prediction != item.team2_prediction && item.team2_prediction != -1 && item.team2_prediction != null) it.team2_prediction = item.team2_prediction
            }
        })
    }


}