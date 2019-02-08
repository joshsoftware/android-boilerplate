package com.amit.app.ui.home

import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityHomeBinding
import com.amit.app.ui.base.BaseActivity
import com.amit.app.ui.main.normallisting.ListActivity
import com.amit.app.ui.main.listingwithdatabinding.ListWithDatabindingActivity
import com.amit.app.util.ActivityManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override fun getToolbarTitle(): String? {
        return "Home"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun init() {
        butttonListingNormal.setOnClickListener {
            ActivityManager.startActivity(this@HomeActivity, ListActivity::class.java)
            startFwdAnimation(this@HomeActivity)
        }
        buttonListingDatabinding.setOnClickListener {
            ActivityManager.startActivity(this@HomeActivity, ListWithDatabindingActivity::class.java)
            startFwdAnimation(this@HomeActivity)
        }
    }

    override fun initLiveDataObservables() {
    }
}
