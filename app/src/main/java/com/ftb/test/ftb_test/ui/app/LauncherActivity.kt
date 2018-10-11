package com.ftb.test.ftb_test.ui.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ftb.test.ftb_test.R
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import java.lang.System.exit

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }


//
//    val navigator = SupportFragmentNavigator(supportFragmentManager,
//            R.id.content_container) {
//        override protected fun Fragment createFragment(screenKey: String, data: Any?):Fragment  {
//        when(screenKey) {
//            case LIST_SCREEN :
//                return ListFragment.getNewInstance(data);
//            case DETAILS_SCREEN :
//                return DetailsFragment.getNewInstance(data);
//            default:
//                throw new RuntimeException (
//                “Unknown screen key
//            !”);
//        }
//    }
//
//        @Override
//        protected void showSystemMessage(String message) {
//            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected void exit() {
//            finish();
//        }
    };

    @Override
    override fun onResume() {
        super.onResume();
        SampleApplication.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    override fun onPause() {
        super.onPause();
        SampleApplication.INSTANCE.getNavigatorHolder().removeNavigator();
}
