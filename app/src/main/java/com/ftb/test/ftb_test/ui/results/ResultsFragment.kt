package com.ftb.test.ftb_test.ui.results

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.data.models.ResultBase
import com.ftb.test.ftb_test.presenters.ResultsPresenter
import com.ftb.test.ftb_test.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
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

    lateinit var recyclerView: RecyclerView
    val resultsAdapter = ResultsAdapter()

   override fun onAttach(context: Context?) {
       AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_results, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.adapter = resultsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        var returnButton = root.findViewById<Button>(R.id.bt_return)
        returnButton.setOnClickListener { presenter.returnButtonClicked()}
        return root
    }

    override fun setData(items: List<ResultBase>) {
        resultsAdapter.setData(items)
        resultsAdapter.notifyDataSetChanged()
    }
}