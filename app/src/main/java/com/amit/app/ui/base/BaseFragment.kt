package com.amit.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.amit.app.util.ApplicationConstant


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    var mViewDataBinding: T? = null
    protected lateinit var mViewModel: V
    private lateinit var mRootView: View

    protected var mActivity: BaseActivity<*, *>? = null

    /**
     * To get layout file
     */
    protected abstract fun getLayout(): Int

    /**
     * To check activity needs a toolbar
     */
    protected var mToolbarRequired: Boolean = true

    protected abstract fun getToolBarTitle(): String

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    abstract fun init()

    /**
     * To initialise live data observables
     */
    abstract fun initLiveDataObservables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
        mRootView = mViewDataBinding!!.root
        mViewModel.getErrorMessage().observe(this, errorMessageObserver)
        mViewModel.getProgress().observe(this, progressObserver)
        init()
        initLiveDataObservables()
        setToolbarTitle()
        return mRootView
    }


    /**
     * To set title for toolbar
     */
    private fun setToolbarTitle() {
        if (mToolbarRequired) {
            (activity as BaseActivity<*, *>).showToolbar()
            (activity as BaseActivity<*, *>).setToolbarTitle(getToolBarTitle())
        } else {
            (activity as BaseActivity<*, *>).hideToolbar()
        }
    }

//    /**
//     * To getLoggedInUser
//     */
//    fun getLoggedInUser(): LoggedInUser {
//        return mActivity!!.getLoggedInUser()
//    }

    /**
     * Fragment transaction.
     *
     * @param container        the container
     * @param transactionType  the transaction type
     * @param fragment         the fragment
     * @param isAddToBackStack the is add to back stack
     * @param tag              the tag
     */
    fun fragmentTransaction(container: Int, transactionType: Int,
                            fragment: Fragment, isAddToBackStack: Boolean, tag: String) {
        val trans = fragmentManager!!.beginTransaction()
        when (transactionType) {
            ApplicationConstant.ADD_FRAGMENT -> {
                trans.add(container, fragment, tag)
                if (isAddToBackStack)
                    trans.addToBackStack(tag)
            }
            ApplicationConstant.REPLACE_FRAGMENT -> {
                trans.replace(container, fragment, tag)
                if (isAddToBackStack)
                    trans.addToBackStack(tag)
            }
            ApplicationConstant.REMOVE_FRAGMENT -> {
                trans.remove(fragment)
                fragmentManager!!.popBackStack()
            }
        }
        trans.commit()
    }

    /**
     * To handle error
     */
    protected val errorMessageObserver: Observer<String> = Observer<String> { t ->
        //        Logger.e(BaseFragment::class.java, t.toString())
        (activity as BaseActivity<*, *>).showToast(t.toString())
    }

    private val progressObserver: Observer<Boolean> = Observer<Boolean> {
        if (it!!)
            (activity as BaseActivity<*, *>).showLoader()
        else
            (activity as BaseActivity<*, *>).hideLoader()
    }


}