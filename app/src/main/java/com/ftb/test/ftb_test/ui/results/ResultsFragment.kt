package com.ftb.test.ftb_test.ui.results

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
import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import javax.inject.Inject
import javax.inject.Provider

class ResultsFragment: BaseFragment(), ResultsView {
    @Inject
    lateinit var presenterProvider: Provider<ResultsPresenter>

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: ResultsPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providePresenter(): ResultsPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

   override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        recycler_view.adapter = ResultsAdapter()
        recycler_view.layoutManager = LinearLayoutManager(context)
        return root
    }

    override fun setData(items: List<ResultBase>) {
        (recycler_view.adapter as ResultsAdapter).setData(items)
    }
}