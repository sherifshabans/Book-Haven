package com.elsharif.bookhaven.utlis

sealed class MyResponsers <T>(
    val data :T?=null,
    val errorMessage:String?=null,
    val progress :Int =0
) {

    class Loading <T> (private val mProgress:Int =0):MyResponsers<T>(progress = mProgress)

    class Success <T> (private val mData:T?):MyResponsers<T>(data = mData)

    class Error <T> (private val mErrorMessage: String?):MyResponsers<T>(errorMessage = mErrorMessage)

}