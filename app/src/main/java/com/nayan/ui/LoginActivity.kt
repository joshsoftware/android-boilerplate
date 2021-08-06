package com.nayan.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.nayan.AppSharedPreferences
import com.nayan.app.R
import com.nayan.app.databinding.ActivityLoginBinding
import com.nayan.viewmodel.BaseViewModel
import com.nayan.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var sharedPreferences: AppSharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    /**
     * Necessary to register for common error and loader observers
     */
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initializeUi() {
        binding.apply {
            // Write your ui interaction code here
        }
    }

    override fun initViewBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
    }

}