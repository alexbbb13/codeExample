package com.ftb.test.ftb_test.ui.results

import com.arellomobile.mvp.MvpView
import com.ftb.test.ftb_test.data.models.ResultBase

interface ResultsView : MvpView {
    fun setData(items: List<ResultBase>)
}