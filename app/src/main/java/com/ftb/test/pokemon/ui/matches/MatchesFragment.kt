package com.ftb.test.pokemon.ui.matches

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
import com.ftb.test.pokemon.R
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.presenters.MatchesPresenter
import com.ftb.test.pokemon.ui.base.BaseFragment
import com.ftb.test.pokemon.ui.dialogs.TwoButtonDialogFragment
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Provider

class MatchesFragment: BaseFragment(), MatchesView, TwoButtonDialogFragment.OnDialogClickedListener {

    @Inject
    lateinit var presenterProvider: Provider<MatchesPresenter>

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: MatchesPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providePresenter(): MatchesPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    val adapter = PokemonsAdapter(listener = {presenter.selectedMatch(it)}, picasso = Picasso.get())

    lateinit var resultsButton: Button;

   override fun onAttach(context: Context?) {
       super.onAttach(context)
       AndroidSupportInjection.inject(this) //before super

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        resultsButton = root.findViewById<Button>(R.id.bt_results)
        resultsButton.setOnClickListener { presenter.resultsButtonClicked()}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return root
    }

    override fun setData(items: List<PokemonBase>) {
        adapter.setData(items)
        adapter.notifyDataSetChanged()
    }

    override fun beginMatchSelection(team1: String, team2: String, team1_prediction: Int?, team2_prediction: Int?) {
        val dialog = TwoButtonDialogFragment.newInstance(TwoButtonDialogFragment.Arguments(team1, team2, team1_prediction, team2_prediction))
        dialog.setTargetFragment(this, 0)
        dialog.show(fragmentManager, "MatchesFragment")
    }

    override fun onDialogChoiceClick(choice: Int, team1Name:String, team2Name: String, team1Score: Int, team2Score: Int) {
        presenter.onDialogChoiceClick(choice, team1Name, team2Name, team1Score, team2Score)
    }

    override fun switchResultsButton(predictionsExist: Boolean) {
        resultsButton.visibility = if(predictionsExist) View.VISIBLE else View.GONE
    }

}