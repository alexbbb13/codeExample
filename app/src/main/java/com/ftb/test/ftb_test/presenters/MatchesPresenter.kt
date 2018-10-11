package com.ftb.test.ftb_test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.ftb_test.data.models.Data
import com.ftb.test.ftb_test.data.models.MatchBase
import com.ftb.test.ftb_test.interactors.MatchesInteractor
import com.ftb.test.ftb_test.ui.matches.MatchesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onComplete
import java.util.*

@InjectViewState
class LoanInfoPresenter constructor(val interactor: MatchesInteractor) : MvpPresenter<MatchesView>() {

    override fun attachView(view: MatchesView?) {
        super.attachView(view)
        interactor.getMatches()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: List <MatchBase>){

    }

    private fun onError(t: Throwable){
        t.printStackTrace()
    }

    private fun transform(baseList: List<MatchBase>): List <Data>{
        return baseList.map {  }
    }

}