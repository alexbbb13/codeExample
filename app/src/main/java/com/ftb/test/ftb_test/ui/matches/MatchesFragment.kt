package com.ftb.test.ftb_test.ui.matches

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.data.localstorage.matches.MatchesBaseDb
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.matches_fragment.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.matches_fragment, container, false)
        recycler_view.adapter = MatchesAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        return root
    }

    override fun setData(items: List<MatchesBase>) {
        (recycler_view.adapter as MatchesAdapter).setData(items)
    }
}