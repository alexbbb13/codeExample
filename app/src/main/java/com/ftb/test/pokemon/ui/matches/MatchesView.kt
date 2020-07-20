package com.ftb.test.pokemon.ui.matches

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ftb.test.pokemon.data.models.PokemonBase

interface MatchesView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun setData(items: List<PokemonBase>)

    @StateStrategyType(SkipStrategy::class)
    fun beginMatchSelection(team1: String, team2: String, team1_prediction: Int?, team2_prediction: Int?)

    @StateStrategyType(SkipStrategy::class)
    fun switchResultsButton(predictionsExist: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun notifyItemInserted(scrollPosition: Int)

    @StateStrategyType(SkipStrategy::class)
    fun notifyItemRemoved(scrollPosition: Int)

    @StateStrategyType(SkipStrategy::class)
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)

    fun notifyDataSetChanged()
    fun notifyItemRangeChanged(positionStart: Int, itemCount: Int)
}