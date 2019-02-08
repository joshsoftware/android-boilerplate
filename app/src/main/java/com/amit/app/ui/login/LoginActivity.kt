package com.amit.app.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.data.model.api.response.LoginResponse
import com.amit.app.data.model.local.binding.LoginUser
import com.amit.app.databinding.ActivityLoginBinding
import com.amit.app.ui.base.BaseActivity
import com.amit.app.ui.home.HomeActivity
import com.amit.app.util.ActivityManager
import com.amit.app.util.DebugLog

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getToolbarTitle(): String? {
        return "Login"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun init() {
        mViewDataBinding!!.loginUser = LoginUser()
    }

    override fun initLiveDataObservables() {
        mViewModel.getLoginResponse().observe(this, loginResponseObserver)
    }

    private val loginResponseObserver: Observer<LoginResponse> = Observer { t ->
        DebugLog.e(t.token)

        //navigating to Home activity
        ActivityManager.startActivity(this@LoginActivity, HomeActivity::class.java)
        startFwdAnimation(this@LoginActivity)
    }

}
