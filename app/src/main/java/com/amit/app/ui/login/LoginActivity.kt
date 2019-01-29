package com.amit.app.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.data.model.local.binding.LoginUser
import com.amit.app.databinding.ActivityLoginBinding
import com.amit.app.ui.base.BaseActivity
import com.amit.app.ui.main.ListActivity
import com.amit.app.ui.main.ListWithDatabindingActivity
import com.amit.app.util.ActivityManager

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

    private val navigateObserver: Observer<Int> = Observer { t ->
        when (t) {
            1 -> {
                ActivityManager.startActivity(this@LoginActivity, ListActivity::class.java)
                startFwdAnimation(this@LoginActivity)
            }
            else -> {

            }
        }
    }

    override fun initLiveDataObservables() {
        mViewModel.getErrorMessage().observe(this, errorMessageObserver)
        mViewModel.getPageRedirection().observe(this, navigateObserver)
    }

}
