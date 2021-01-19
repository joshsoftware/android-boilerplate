package com.amit.ui.splash

import androidx.lifecycle.ViewModel
import com.amit.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashModel(viewModel: SplashViewModel): ViewModel
}