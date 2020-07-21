package com.ftb.test.pokemon.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.ftb.test.pokemon.application.FtbApplication
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.ResultBase
import com.ftb.test.pokemon.interactors.PokemonsInteractor
import com.ftb.test.pokemon.interactors.ResultsInteractor
import com.ftb.test.pokemon.navigation.FtbNavigator
import com.ftb.test.pokemon.ui.results.ResultsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ResultsPresenter constructor(val interactor: PokemonsInteractor) : MvpPresenter<ResultsView>() {

    var id: Int = 1

    override fun attachView(view: ResultsView?) {
        super.attachView(view)
        interactor.getPokemonsForId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onComplete, this::onError)
    }

    private fun onComplete(data: PokemonBase){
        viewState.setData(data)
    }

    private fun onError(t: Throwable){
        t.printStackTrace()
    }

    fun returnButtonClicked() {
        FtbApplication.INSTANCE.getRouter().replaceScreen(FtbNavigator.MATCHES)
    }

    fun setArgs(id: Int) {
        this.id = id
    }


}