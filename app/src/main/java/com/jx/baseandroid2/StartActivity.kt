package com.jx.baseandroid2

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.alibaba.android.arouter.launcher.ARouter

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        //ARouter.getInstance().build("/uiabout/AboutActivity")
        ARouter.getInstance().build("/uimain/MainActivity")
        //ARouter.getInstance().build("/uilogin/LoginActivity")
            .withInt("value1", 111)
            .withBoolean("value2", false)
            .navigation()
    }
}
