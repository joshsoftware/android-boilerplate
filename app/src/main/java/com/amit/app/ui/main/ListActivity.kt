package com.amit.app.ui.main

import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityListBinding
import com.amit.app.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list_with_databinding.*

class ListActivity : BaseActivity<ActivityListBinding, MainViewModel>() {
    private lateinit var mAdapter: MainSimpleAdapter

    override fun getToolbarTitle(): String? {
        return "Home"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun init() {
    }

    override fun initLiveDataObservables() {
        initializeSimpleMainAdapter()
    }

    private fun initializeSimpleMainAdapter() {
        mAdapter = MainSimpleAdapter(mViewModel.getDataList())
        recyclerViewStudents.adapter = mAdapter
    }
}
