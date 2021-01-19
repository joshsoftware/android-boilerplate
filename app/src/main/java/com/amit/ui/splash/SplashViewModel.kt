package com.amit.ui.splash

import android.content.Context
import com.amit.data.network.repository.Repository
import com.amit.ui.base.BaseViewModel
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    context: Context,
    private val dataAmountRepository: Repository
) : BaseViewModel() {
}