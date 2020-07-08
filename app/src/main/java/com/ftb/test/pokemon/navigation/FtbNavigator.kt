package com.ftb.test.pokemon.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.ftb.test.pokemon.extra.extraKey
import com.ftb.test.pokemon.ui.matches.MatchesFragment
import com.ftb.test.pokemon.ui.results.ResultsFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

class FtbNavigator(val manager: FragmentManager, private val containerId: Int): SupportFragmentNavigator(manager, containerId) {

    override fun exit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSystemMessage(message: String?) {
    }

    companion object {
        val MATCHES by extraKey()
        val RESULTS by extraKey()
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            MATCHES -> MatchesFragment()
            RESULTS -> ResultsFragment()
            else -> {
                throw RuntimeException("Key $screenKey is not valid")
            }
        }
    }
}