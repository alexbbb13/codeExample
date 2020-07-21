package com.ftb.test.pokemon.ui.results

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ftb.test.pokemon.R
import com.ftb.test.pokemon.data.models.PokemonBase
import com.ftb.test.pokemon.data.models.ResultBase
import com.ftb.test.pokemon.extra.extraKey
import com.ftb.test.pokemon.presenters.ResultsPresenter
import com.ftb.test.pokemon.ui.base.BaseFragment
import com.squareup.picasso.Picasso
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
        val id = arguments!!.getInt(ARGS)
        val presenter = presenterProvider.get()
        presenter.setArgs(id)
        return presenter
    }

    lateinit var pokeName: TextView
    lateinit var pokeImage: ImageView
    lateinit var pokeType: TextView
    lateinit var pokeHeight: TextView
    lateinit var pokeWeight: TextView
    lateinit var pokeAttack: TextView
    lateinit var pokeDefence: TextView

   override fun onAttach(context: Context?) {
       AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_results, container, false)
        pokeName = root.findViewById(R.id.poke_name)
        pokeImage = root.findViewById(R.id.poke_image)
        pokeType = root.findViewById(R.id.results_type_value)
            pokeHeight = root.findViewById(R.id.results_height_value)
            pokeWeight = root.findViewById(R.id.results_weight_value)
            pokeAttack = root.findViewById(R.id.results_attack_value)
            pokeDefence = root.findViewById(R.id.results_defense_value)
        return root
    }

    override fun setData(pokemonBase: PokemonBase) {
        with(pokemonBase) {
            pokeName.text = name
            Picasso.get().load(pictureUrl).into(pokeImage)
            pokeType.text = type
            pokeHeight.text = height.toString()
            pokeWeight.text = weight.toString()
            pokeAttack.text = attack.toString()
            pokeDefence.text = defence.toString()
        }
    }

    companion object {

        val ARGS by extraKey()

        fun getInstance(id: Int): ResultsFragment {
            val fragment = ResultsFragment()
            val args = Bundle()
            args.putInt(ARGS, id)
            fragment.setArguments(args)
            return fragment
        }
    }
}