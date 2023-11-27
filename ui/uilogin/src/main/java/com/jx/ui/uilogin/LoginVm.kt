package com.jx.ui.uilogin

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginVm : ViewModel() {
    //val username2 = ObservableField("")
    //val username2 = MutableLiveData("")
    var userName = MutableLiveData("")
    var pwd = MutableLiveData("")

    fun change(view: View) {
        Log.d("LoginVm", "${userName.value}  ${pwd.value}")
        userName.value = "default username"
        pwd.value = "default pwd"
    }
}