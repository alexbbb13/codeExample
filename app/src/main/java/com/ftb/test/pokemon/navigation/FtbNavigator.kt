package com.ftb.test.pokemon.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.ftb.test.pokemon.extra.extraKey
import com.ftb.test.pokemon.ui.matches.PokemonsFragment
import com.ftb.test.pokemon.ui.results.ResultsFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

class FtbNavigator(val manager: FragmentManager, private val containerId: Int): SupportFragmentNavigator(manager, containerId) {

    override fun exit() {
        //on Exit
    }

    override fun showSystemMessage(message: String?) {
    }

    companion object {
        val ALL_POKEMONS by extraKey()
        val POKEMON_DETAILS by extraKey()
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            ALL_POKEMONS -> PokemonsFragment()
            POKEMON_DETAILS -> ResultsFragment.getInstance(data as Int)
            else -> {
                throw RuntimeException("Key $screenKey is not valid")
            }
        }
    }
}