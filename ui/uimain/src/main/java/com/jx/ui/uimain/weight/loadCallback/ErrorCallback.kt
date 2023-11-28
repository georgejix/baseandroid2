package com.jx.ui.uimain.weight.loadCallback

import com.jx.ui.uimain.R
import com.kingja.loadsir.callback.Callback

class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}