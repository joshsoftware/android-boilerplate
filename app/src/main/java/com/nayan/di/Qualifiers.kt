package com.nayan.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Http

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PreLogin