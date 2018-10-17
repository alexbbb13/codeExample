package com.ftb.test.ftb_test.ui.matches

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.R.id.recycler_view
import com.ftb.test.ftb_test.data.models.MatchesBase
import com.ftb.test.ftb_test.presenters.MatchesPresenter
import com.ftb.test.ftb_test.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_recyclerview.*
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
       AndroidSupportInjection.inject(this) //before super

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_recyclerview, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = MatchesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        //presenter.onMatchClick(MatchesBase("","",0,2,3))
        return root
    }

    override fun setData(items: List<MatchesBase>) {
        (recycler_view.adapter as MatchesAdapter).setData(items)
    }
}