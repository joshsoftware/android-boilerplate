package com.amit.ui.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.amit.app.R
import com.amit.custom.circularprogressview.CustomProgressDialog
import com.amit.util.Constants
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity() {

    private var spinningDialog: Dialog? = null

    var mViewDataBinding: T? = null

    protected lateinit var mViewModel: V

    /**
     * To check activity needs a toolbar (if not then need to specify as false)
     */
    protected var mToolbarRequired: Boolean = isToolBarRequired()

    /**
     * TO manage visibility for navigation button
     */
    protected var mNavigationButtonRequired: Boolean = true

    /**
     * To handle actions on toolbar
     */
    lateinit var mToolbar: Toolbar
    lateinit var mToolbarTitle: TextView
    lateinit var mToolbarNavigationIcon: AppCompatImageView

    /**
     * This method is used to define toolbar title
     */
    abstract fun getToolbarTitle(): String?

    /**
     * This method is used to define layout id (Ex: R.layout.activity_login)
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun isToolBarRequired(): Boolean

    /**
     * Override for set view model
     * @return view model instance
     */
    abstract fun getViewModel(): V

    /**
     * Override for set binding variable
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * This method will be the starting point in all the other activities.
     */
    abstract fun init()

    /**
     * To initialise live data observables
     */
    abstract fun initLiveDataObservables()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        //toolbar
        initializeToolbar()
        init() // In all activities, this method will be called first.
        initLiveDataObservables()
        mViewModel.getProgress().observe(this, progressObserver)
        mViewModel.getMessage().observe(this, messageObserver)
        mViewModel.getAuthorizationFailedListener().observe(this, authorizationFailedObserver)

    }

    private fun initializeToolbar() {
        //set toolbar
        if (mToolbarRequired) {
            setToolBar()
        }
    }

    /**
     * To perform data binding operation
     */
    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (!::mViewModel.isInitialized) getViewModel() else mViewModel
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    /**
     * To set tool bar
     */
    fun setToolBar() {
//        mToolbar = findViewById(getToolbarRes())
        mToolbar = mViewDataBinding!!.root.findViewById(R.id.toolbar)

        setSupportActionBar(mToolbar)
        mToolbarNavigationIcon = mToolbar.findViewById(R.id.ivNavigation) as AppCompatImageView
        if (mNavigationButtonRequired) {
            mToolbarNavigationIcon.setImageResource(R.drawable.ic_arrow_back_black)
            mToolbarNavigationIcon.visibility = View.VISIBLE
            mToolbarNavigationIcon.setOnClickListener {
                onBackPressed()
            }
        } else {
            mToolbarNavigationIcon.setOnClickListener { null }
            mToolbarNavigationIcon.visibility = View.GONE
        }

        if (getToolbarTitle() != null) {
            mToolbarTitle = mToolbar.findViewById(R.id.tvTitle) as TextView
            mToolbarTitle.text = getToolbarTitle()
        }
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    protected fun handleAuthorizationFailed() {
//        try {
//            SharedPreferenceManager.clearUserInfo(this)
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    /**
     * To load progress dialog on screen
     */
    fun showLoader() {
        try {
            if (spinningDialog == null) {
                spinningDialog = CustomProgressDialog.showProgressDialog(this@BaseActivity)
            }
            spinningDialog!!.setCancelable(false)
            spinningDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * To hide showing dialog
     */
    fun hideLoader() {
        spinningDialog?.let { if (it.isShowing) it.cancel() }
    }

    /**
     * To hide toolbar
     */
    fun hideToolbar() {
        if (::mToolbar.isInitialized)
            mToolbar.visibility = View.GONE
    }

    /**
     * To display toolbar
     */
    fun showToolbar() {
        if (::mToolbar.isInitialized)
            mToolbar.visibility = View.VISIBLE
    }

    fun showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    /**
     * To set Toolbar title
     */
    fun setToolbarTitle(toolBarTitle: String) {
        mToolbarTitle.text = toolBarTitle
    }

    /**
     * To handle error
     */
    protected val messageObserver: Observer<String> = Observer<String> { t ->
        //        Logger.e(BaseFragment::class.java, t.toString())
        showToast(t.toString())
    }
    private val progressObserver: Observer<Boolean> = Observer<Boolean> {
        if (it!!)
            showLoader()
        else
            hideLoader()
    }

    protected val authorizationFailedObserver: Observer<Boolean> = Observer {
        handleAuthorizationFailed()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    private fun overridePendingTransitionExit() {
    }

    /**
     * Fragment transaction.
     *
     * @param container        the container
     * @param transactionType  the transaction type
     * @param fragment         the fragment
     * @param isAddToBackStack the is ic_add_to_cart to ic_back stack
     * @param tag              the tag
     */
    open fun fragmentTransaction(
        container: Int, transactionType: Int,
        fragment: Fragment, isAddToBackStack: Boolean, tag: String?
    ) {
        val trans: FragmentTransaction = supportFragmentManager.beginTransaction()
//        trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right)
        when (transactionType) {
            Constants.ADD_FRAGMENT -> {
                trans.add(container, fragment, tag)
                if (isAddToBackStack) trans.addToBackStack(tag)
            }
            Constants.REPLACE_FRAGMENT -> {
                trans.replace(container, fragment, tag)
                if (isAddToBackStack) trans.addToBackStack(tag)
            }
            Constants.REMOVE_FRAGMENT -> {
                trans.remove(fragment)
                supportFragmentManager.popBackStack()
            }
        }
        trans.commit()
    }
}