package com.nayan.di

import com.nayan.factory.ErrorHandlerFactory
import com.nayan.factory.IFactory
import com.nayan.model.RequestType
import com.nayan.network.errorhandling.ErrorHandler
import com.nayan.network.errorhandling.HttpErrorHandler
import com.nayan.network.errorhandling.PreLoginErrorHandler
import com.nayan.repository.IUserRepository
import com.nayan.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindIErrorHandlerFactory(errorHandlerFactory: ErrorHandlerFactory): IFactory<RequestType, ErrorHandler>

    @Binds
    @Http
    abstract fun bindIHttpErrorHandler(httpErrorHandler: HttpErrorHandler): ErrorHandler

    @Binds
    @PreLogin
    abstract fun bindPreLoginErrorHandler(preLoginErrorHandler: PreLoginErrorHandler): ErrorHandler

    @Binds
    abstract fun bindIUserRepository(userRepository: UserRepository): IUserRepository
}