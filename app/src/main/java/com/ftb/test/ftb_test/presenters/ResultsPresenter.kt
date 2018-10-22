package com.ftb.test.ftb_test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.interactors.ResultsInteractor
import com.ftb.test.ftb_test.navigation.FtbNavigator
import com.ftb.test.ftb_test.ui.results.ResultsView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ResultsPresenter constructor(val interactor: ResultsInteractor) : MvpPresenter<ResultsView>() {

    lateinit var cachedData :List <ResultBase>;

    override fun attachView(view: ResultsView?) {
        super.attachView(view)
        interactor.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: List <ResultBase>){
        cachedData = data;
        store(data)
        viewState.setData(data)
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
}