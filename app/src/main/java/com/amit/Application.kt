package com.amit

import com.amit.data.model.local.prefs.PreferenceStorage
import com.amit.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

/**
 * Initialization of libraries.
 */
class Application : DaggerApplication() {

    @Inject
    lateinit var preferenceStorage: PreferenceStorage

    override fun onCreate() {
        super.onCreate()

    }
    /**
     * Tell Dagger which [AndroidInjector] to use - in our case
     * [com.base.di.AppComponent]. `DaggerAppComponent`
     * is a class generated by Dagger based on the `AppComponent` class.
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
