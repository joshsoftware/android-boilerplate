package com.amit.ui.home

import androidx.lifecycle.ViewModelProvider
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityHomeBinding
import com.amit.ui.base.BaseActivity
import com.amit.ui.main.listingwithdatabinding.ListWithDatabindingActivity
import com.amit.ui.main.normallisting.ListActivity
import com.amit.util.ActivityManager
import com.amit.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun getToolbarTitle(): String? {
        return "Home"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun init() {
        butttonListingNormal.setOnClickListener {
            ActivityManager.startActivity(this@HomeActivity, ListActivity::class.java)
        }
        buttonListingDatabinding.setOnClickListener {
            ActivityManager.startActivity(
                this@HomeActivity,
                ListWithDatabindingActivity::class.java
            )
        }
    }

    override fun initLiveDataObservables() {
    }

    override fun isToolBarRequired(): Boolean = true
}
