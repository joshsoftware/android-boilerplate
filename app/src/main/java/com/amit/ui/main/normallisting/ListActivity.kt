package com.amit.ui.main.normallisting

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityListBinding
import com.amit.data.model.api.response.User
import com.amit.data.model.api.response.UserListResponse
import com.amit.ui.main.MainViewModel
import com.amit.ui.base.BaseActivity
import com.amit.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : BaseActivity<ActivityListBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mAdapter: MainSimpleAdapter
    private val users = ArrayList<User>()
    override fun getToolbarTitle(): String? {
        return "UserList Normal adapter"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list
    }

    override fun getViewModel(): MainViewModel {
        return viewModelProvider(viewModelFactory)
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
        users.addAll(t.data)
        mAdapter.notifyDataSetChanged()
    }


    private fun initializeSimpleMainAdapter() {
        mAdapter = MainSimpleAdapter(users)
        recyclerViewStudents.adapter = mAdapter
    }

    override fun isToolBarRequired(): Boolean = true
}
