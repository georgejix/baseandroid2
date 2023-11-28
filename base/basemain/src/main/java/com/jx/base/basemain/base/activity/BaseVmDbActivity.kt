package com.jx.base.basemain.base.activity

import android.view.View
import androidx.databinding.ViewDataBinding
import com.jx.base.basemain.base.viewmodel.BaseViewModel
import com.jx.base.basemain.ext.inflateBindingWithGeneric

abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    override fun layoutId() = 0

    lateinit var mDatabind: DB

    /**
     * 创建DataBinding
     */
    override fun initDataBind(): View? {
        mDatabind = inflateBindingWithGeneric(layoutInflater)
        return mDatabind.root
    }
}