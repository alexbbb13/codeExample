package com.ftb.test.pokemon.ui.results

import com.arellomobile.mvp.MvpView
import com.ftb.test.pokemon.data.models.ResultBase

interface ResultsView : MvpView {
    fun setData(items: List<ResultBase>)
}