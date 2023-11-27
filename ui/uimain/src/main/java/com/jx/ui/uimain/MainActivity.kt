package com.jx.ui.uimain

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jx.ui.uimain.ui.theme.BaseAndroid2Theme

@Route(path = "/uimain/MainActivity")
class MainActivity : ComponentActivity() {
    val TAG = "MainActivity"

    @JvmField
    @Autowired
    var value1: Int = 0

    @JvmField
    @Autowired(name = "value2")
    var value2_: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "value1 = ${value1} value2= ${value2_}")
    }
}
