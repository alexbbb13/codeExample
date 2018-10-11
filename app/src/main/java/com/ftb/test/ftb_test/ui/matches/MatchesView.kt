package com.ftb.test.ftb_test.ui.matches

import com.arellomobile.mvp.MvpView
import com.ftb.test.ftb_test.data.models.Data

interface MatchesView : MvpView {
    fun setData(items: List<Data>)
}