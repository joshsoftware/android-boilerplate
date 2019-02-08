package com.amit.app.ui.main.normallisting

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.data.model.api.response.User
import com.amit.app.data.model.api.response.UserListResponse
import com.amit.app.databinding.ActivityListBinding
import com.amit.app.ui.base.BaseActivity
import com.amit.app.ui.main.MainViewModel
import com.amit.app.util.DebugLog
import kotlinx.android.synthetic.main.activity_list_with_databinding.*

class ListActivity : BaseActivity<ActivityListBinding, MainViewModel>() {
    private lateinit var mAdapter: MainSimpleAdapter
    private val users = ArrayList<User>()
    override fun getToolbarTitle(): String? {
        return "UserList Normal adapter"
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
        initializeSimpleMainAdapter()
    }

    override fun initLiveDataObservables() {

        mViewModel.getUserListResponse().observe(this, userListResponseObserver)
    }

    private val userListResponseObserver: Observer<in UserListResponse> = Observer { t ->
        DebugLog.e(t.data.size.toString())
        users.addAll(t.data)
        mAdapter.notifyDataSetChanged()
    }


    private fun initializeSimpleMainAdapter() {
        mAdapter = MainSimpleAdapter(users)
        recyclerViewStudents.adapter = mAdapter
    }
}
