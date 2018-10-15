package com.ftb.test.ftb_test.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.ftb.test.ftb_test.extra.extraKey
import com.ftb.test.ftb_test.ui.matches.MatchesFragment
import com.ftb.test.ftb_test.ui.results.ResultsFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import java.lang.RuntimeException

open class FtbNavigator(val activity: FragmentActivity, private val containerId: Int): SupportAppNavigator(activity, containerId) {

    override fun createActivityIntent(screenKey: String?, data: Any?): Intent {
        return Intent()
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