package com.jx.ui.uimain.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jx.ui.uimain.base.BaseActivity
import com.jx.ui.uimain.databinding.ActivityMainBinding
import com.jx.ui.uimain.viewmodel.MainViewModel

@Route(path = "/uimain/MainActivity")
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    val TAG = "MainActivity"

    @JvmField
    @Autowired
    var value1: Int = 0

    @JvmField
    @Autowired(name = "value2")
    var value2_: Boolean = true

    override fun initView(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
    }

    override fun createObserver() {
        super.createObserver()
    }
}
