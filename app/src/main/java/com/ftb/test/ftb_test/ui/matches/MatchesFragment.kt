package com.ftb.test.ftb_test.ui.matches

import android.content.Context
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.ftb_test.data.models.Data
import com.ftb.test.ftb_test.data.room.matches.MatchesBaseDb
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_matches.*
import javax.inject.Inject
import javax.inject.Provider

class MatchesFragment: BaseFragment(), MatchesView {
    @Inject
    lateinit var presenterProvider: Provider<MatchesPresenter>

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: MatchesPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providePresenter(): MatchesPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun setData(items: List<MatchesBaseDb>) {
        rv_container.adapter.setData(items)
    }
}