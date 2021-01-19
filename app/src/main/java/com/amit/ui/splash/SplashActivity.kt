package com.amit.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.amit.app.BR
import com.amit.app.R
import com.amit.app.databinding.ActivitySplashBinding
import com.amit.ui.base.BaseActivity
import com.amit.ui.login.LoginActivity
import com.amit.util.ActivityManager
import com.amit.util.viewModelProvider
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mSplashViewModel: SplashViewModel
    override fun getToolbarTitle(): String? {
        return ""
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        mSplashViewModel = viewModelProvider(viewModelFactory) as SplashViewModel
        return mSplashViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        Handler().postDelayed({
            ActivityManager.startFreshActivityClearStack(
                this@SplashActivity,
                LoginActivity::class.java
            )
        }, 2000)
    }

    override fun initLiveDataObservables() {
    }

    override fun isToolBarRequired(): Boolean = false

}
