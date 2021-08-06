package com.nayan.factory

interface IFactory<T, R> {
    fun create(data: T): R
}