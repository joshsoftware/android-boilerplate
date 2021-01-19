package com.amit.di

import com.amit.core.di.ActivityScoped
import com.amit.ui.home.HomeActivity
import com.amit.ui.home.HomeModule
import com.amit.ui.login.LoginActivity
import com.amit.ui.login.LoginModule
import com.amit.ui.main.MainModule
import com.amit.ui.main.listingwithdatabinding.ListWithDatabindingActivity
import com.amit.ui.main.normallisting.ListActivity
import com.amit.ui.splash.SplashActivity
import com.amit.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            SplashModule::class
        ]
    )
    @ActivityScoped
    internal abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(
        modules = [
            LoginModule::class
        ]
    )
    @ActivityScoped
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    @ActivityScoped
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(
        modules = [
            MainModule::class
        ]
    )
    @ActivityScoped
    internal abstract fun listwithDatabindingActivity(): ListWithDatabindingActivity

    @ContributesAndroidInjector(
        modules = [
            MainModule::class
        ]
    )
    @ActivityScoped
    internal abstract fun listActivity(): ListActivity


}
