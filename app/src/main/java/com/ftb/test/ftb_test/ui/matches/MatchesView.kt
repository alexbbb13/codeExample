package com.ftb.test.ftb_test.ui.matches

import com.arellomobile.mvp.MvpView
import com.ftb.test.ftb_test.data.models.MatchesBase

interface MatchesView : MvpView {
    fun setData(items: List<MatchesBase>)
}