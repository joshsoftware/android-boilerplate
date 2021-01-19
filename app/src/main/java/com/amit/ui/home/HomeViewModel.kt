package com.amit.ui.home

import android.content.Context
import com.amit.data.network.repository.Repository
import com.amit.ui.base.BaseViewModel
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    context: Context,
    private val repository: Repository
) : BaseViewModel() {
}