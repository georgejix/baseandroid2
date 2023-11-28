package com.jx.ui.uimain.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jx.base.basemain.base.viewmodel.BaseViewModel

class FMainViewModel : BaseViewModel() {

    val btn1 = MutableLiveData("主页")
    val btn2 = MutableLiveData("广场")
    val btn3 = MutableLiveData("消息")
    val btn4 = MutableLiveData("我的")

    val vpPosition = MutableLiveData(0)

    fun jump1(view: View) {
        vpPosition.value = 0
    }

    fun jump2(view: View) {
        vpPosition.value = 1
    }

    fun jump3(view: View) {
        vpPosition.value = 2
    }

    fun jump4(view: View) {
        vpPosition.value = 3
    }
}