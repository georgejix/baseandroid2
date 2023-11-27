package com.jx.ui.uilogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jx.ui.uilogin.databinding.ActivityLoginBinding

@Route(path = "/uilogin/LoginActivity")
class LoginActivity : ComponentActivity() {
    var dataBind: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //databing之后，通过DataBindingUtil设置layout，自动绑定vm和layout
        dataBind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        //继承viewmodel之后，可以自动实例化vm对象
        dataBind?.loginVm = ViewModelProvider(this)[LoginVm::class.java]
        //设置lifecycle，否则vm不会和view绑定
        dataBind?.lifecycleOwner = this
        dataBind?.loginVm?.userName?.value = "12345"
        dataBind?.loginVm?.pwd?.value = "12345"
        ARouter.getInstance().inject(this)

    }

    override fun onResume() {
        super.onResume()
        dataBind?.etUsername?.let { setSelection(it) }
        dataBind?.etPwd?.let { setSelection(it) }
    }

    private fun setSelection(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                editText.setSelection(s.toString().length)
            }
        })
    }
}