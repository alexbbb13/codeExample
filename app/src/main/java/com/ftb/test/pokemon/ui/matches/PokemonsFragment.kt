package com.ftb.test.pokemon.ui.matches

import android.content.Context
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.pokemon.R
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.presenters.PokemonsPresenter
import com.ftb.test.pokemon.ui.base.BaseFragment
import com.ftb.test.pokemon.ui.dialogs.TwoButtonDialogFragment
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Provider

class PokemonsFragment: BaseFragment(), PokemonsView {

    @Inject
    lateinit var presenterProvider: Provider<PokemonsPresenter>

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: PokemonsPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providePresenter(): PokemonsPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    val adapter = PokemonsAdapter(listener = {presenter.selectedMatch(it)}, picasso = Picasso.get())

    lateinit var recyclerView: RecyclerView
    lateinit var resultsButton: Button;

   override fun onAttach(context: Context?) {
       super.onAttach(context)
       AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        resultsButton = root.findViewById<Button>(R.id.bt_results)
        resultsButton.setOnClickListener { presenter.updateButtonClicked()}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(@NonNull recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                presenter.loadMore(linearLayoutManager.findLastCompletelyVisibleItemPosition())
          }
        })
        return root
    }

    override fun setData(items: List<PokemonBase>) {
        adapter.setData(items)
        adapter.notifyDataSetChanged()
    }

    override fun notifyItemInserted(scrollPosition: Int) {
        recyclerView.post { adapter.notifyItemInserted(scrollPosition) }
    }

    override fun notifyItemRemoved(scrollPosition: Int) {
        recyclerView.post {adapter.notifyItemRemoved(scrollPosition)}
    }

    override fun notifyItemRangeInserted(positionStart: Int, itemCount: Int) {
        recyclerView.post { adapter.notifyItemRangeInserted(positionStart,itemCount) }
    }

    override fun notifyItemRangeChanged(positionStart: Int, itemCount: Int) {
        recyclerView.post { adapter.notifyItemRangeChanged(positionStart,itemCount) }
    }
}