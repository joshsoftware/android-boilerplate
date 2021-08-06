package com.nayan.ui

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.nayan.AppSharedPreferences
import com.nayan.app.R
import com.nayan.thirdparty.CustomProgressDialog
import com.nayan.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {
    private var spinningDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initializeCommonObservers(getViewModel())
        initializeUi()
    }

    fun isNetWorkAvailable(onAvailable: () -> Unit, action: () -> Unit = {}) {
        val connMgr = applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            onAvailable()
        } else {
            showSnackBar(getString(R.string.network_error), action)
        }
    }

    private fun initializeCommonObservers(viewModel: BaseViewModel) {
        if (setErrorAndSpinnerObserver()) {
            viewModel.error.observe(this, { message ->
                message?.let(::showError)
            })

            viewModel.spinner.observe(this, { loading ->
                if (loading) {
                    showLoader()
                } else {
                    hideLoader()
                }
            })
        }
    }
    private fun showSnackBar(message: String, action: () -> Unit) {
        val rootView: View = window.decorView.findViewById(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("RETRY") {
                action()
            }
            .show()
    }

    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun showLoader() {
        if (spinningDialog == null) {
            spinningDialog = CustomProgressDialog.showProgressDialog(this@BaseActivity)
        }
        spinningDialog!!.setCancelable(false)
        spinningDialog!!.show()
    }

    private fun hideLoader() {
        spinningDialog?.let { if (it.isShowing) it.cancel() }
    }

    protected open fun setErrorAndSpinnerObserver(): Boolean {
        return true
    }

    abstract fun getViewModel(): BaseViewModel
    abstract fun initializeUi()
    abstract fun initViewBinding()
}