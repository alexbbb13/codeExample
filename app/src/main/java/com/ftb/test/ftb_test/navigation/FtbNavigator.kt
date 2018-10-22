package com.ftb.test.ftb_test.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.ftb.test.ftb_test.extra.extraKey
import com.ftb.test.ftb_test.ui.matches.MatchesFragment
import com.ftb.test.ftb_test.ui.results.ResultsFragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command

class FtbNavigator(val manager: FragmentManager, private val containerId: Int): SupportFragmentNavigator(manager, containerId) {

    override fun exit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSystemMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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