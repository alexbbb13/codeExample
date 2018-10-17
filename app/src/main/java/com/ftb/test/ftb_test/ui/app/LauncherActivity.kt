package com.ftb.test.ftb_test.ui.app

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.application.FtbApplication
import com.ftb.test.ftb_test.extra.extraKey
import com.ftb.test.ftb_test.navigation.FtbNavigator
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import java.lang.System.exit
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {
    companion object {
        private const val FRAGMENT_CONTAINER_ID = R.id.fragment_container
        private val NAVIGATOR_KEY by extraKey()
        private val NAVIGATOR_DATA by extraKey()

        fun createIntent(source: Activity, navigatorKey: String, navigatorData: Parcelable? = null): Intent {
            val intent = Intent(source, LauncherActivity::class.java)
            intent.putExtra(NAVIGATOR_KEY, navigatorKey)
            intent.putExtra(NAVIGATOR_DATA, navigatorData)

            return intent
        }
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy(LazyThreadSafetyMode.NONE) { FtbNavigator(supportFragmentManager, FRAGMENT_CONTAINER_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this) //before calling super!!!!!!!!!!!!!
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        if (savedInstanceState == null) {
            initiateFragment()
        }
    }

    protected open fun initiateFragment() {
        router.newRootScreen(FtbNavigator.MATCHES, null)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
//        val fragment = supportFragmentManager.findFragmentById(FRAGMENT_CONTAINER_ID)
//        if (fragment?.instanceIf<BackButtonListener>()?.consumeBackButton() == true) {
//            return
//        } else {
            router.exit()
//        }
    }
}
