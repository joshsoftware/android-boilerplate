package com.amit.app.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivitySplashBinding
import com.amit.app.ui.base.BaseActivity
import com.amit.app.ui.login.LoginActivity
import com.amit.app.util.ActivityManager

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    init {
        mToolbarRequired = false
    }

    override fun getToolbarTitle(): String? {
        return ""
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        return ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)

    }

    override fun init() {
        Handler().postDelayed({
            ActivityManager.startFreshActivityClearStack(this@SplashActivity, LoginActivity::class.java)
            startFwdAnimation(this@SplashActivity)
        }, 2000)
    }

    override fun initLiveDataObservables() {
    }

}
