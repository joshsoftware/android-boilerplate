package com.amit.app.ui.main

import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityListWithDatabindingBinding
import com.amit.app.ui.base.BaseActivity

class ListWithDatabindingActivity : BaseActivity<ActivityListWithDatabindingBinding, MainViewModel>() {

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initLiveDataObservables() {
    }

    override fun getToolbarTitle(): String? {
        return "Home"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list_with_databinding
    }

    override fun init() {
    }

}
