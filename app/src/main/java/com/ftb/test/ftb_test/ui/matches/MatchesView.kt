package com.ftb.test.ftb_test.ui.matches

import com.arellomobile.mvp.MvpView
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb

interface MatchesView : MvpView {
    fun setData(items: List<MatchesBaseDb>)
}