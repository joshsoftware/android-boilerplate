package com.amit.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivityLoginBinding
import com.amit.data.model.api.response.LoginResponse
import com.amit.data.model.local.binding.LoginUser
import com.amit.ui.base.BaseActivity
import com.amit.ui.home.HomeActivity
import com.amit.util.ActivityManager
import com.amit.util.viewModelProvider
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getToolbarTitle(): String? {
        return "Login"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        return viewModelProvider(viewModelFactory)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun init() {
        mViewDataBinding!!.loginUser = LoginUser()
        mViewDataBinding!!.loginUser?.email = "eve.holt@reqres.in"
        mViewDataBinding!!.loginUser?.password = "cityslicka"
    }

    override fun initLiveDataObservables() {
        mViewModel.getLoginResponse().observe(this, loginResponseObserver)
    }

    private val loginResponseObserver: Observer<LoginResponse> = Observer { t ->

        //navigating to Home activity
        ActivityManager.startFreshActivityClearStack(this@LoginActivity, HomeActivity::class.java)
    }

    override fun isToolBarRequired(): Boolean = true

}
