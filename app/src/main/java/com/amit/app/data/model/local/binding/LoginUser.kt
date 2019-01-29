package com.amit.app.data.model.local.binding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.amit.app.BR

class LoginUser : BaseObservable() {

    @get:Bindable
    var email: String = ""
        set(email) {
            field = email
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String = ""
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }
}