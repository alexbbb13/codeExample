package com.ftb.test.ftb_test.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import com.ftb.test.ftb_test.R
import com.ftb.test.ftb_test.R.id.rv_container
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.fragment_matches.*
import javax.inject.Inject

open class BaseFragment : Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_matches, container, false)

        rv_container.layoutManager = LinearLayoutManager(activity)

        return root
    }

//    override fun setData(items: List<InfoView.Data>) {
//        contentContainer.setupChildren(items.count(),
//                onCreate = { _, context -> TextListRowLayout(context) },
//                onShow = { view, position ->
//                    val item = items[position]
//                    view as TextListRowLayout
//                    view.setText(title = item.title, description = item.description)
//                    if (item.clickable) view.setOnClickListener { itemClicked(item.id) }
//                    view.visibility = View.VISIBLE
//                })
//    }
//
//    override fun setProgressVisibility(visible: Boolean) {
//        progress.visibility = visible.toVisibility()
//    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }
}
