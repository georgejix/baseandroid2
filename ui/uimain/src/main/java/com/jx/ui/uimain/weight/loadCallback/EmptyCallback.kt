package com.jx.ui.uimain.weight.loadCallback


import com.jx.ui.uimain.R
import com.kingja.loadsir.callback.Callback


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}