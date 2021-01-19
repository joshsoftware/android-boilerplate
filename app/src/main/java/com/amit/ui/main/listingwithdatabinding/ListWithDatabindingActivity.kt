package com.amit.ui.main.listingwithdatabinding

import androidx.lifecycle.ViewModelProvider
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityListWithDatabindingBinding
import com.amit.ui.base.BaseActivity
import com.amit.ui.main.MainViewModel
import com.amit.util.viewModelProvider
import javax.inject.Inject

class ListWithDatabindingActivity :
    BaseActivity<ActivityListWithDatabindingBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewModel(): MainViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initLiveDataObservables() {
    }

    override fun getToolbarTitle(): String? {
        return "UserList RecyclerBaseAdapter"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list_with_databinding
    }

    override fun init() {
    }

    override fun isToolBarRequired(): Boolean = true

}
