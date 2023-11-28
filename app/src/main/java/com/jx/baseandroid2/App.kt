package com.jx.baseandroid2

import com.alibaba.android.arouter.launcher.ARouter
import com.jx.base.basemain.base.BaseApp
import com.jx.ui.uimain.weight.loadCallback.EmptyCallback
import com.jx.ui.uimain.weight.loadCallback.ErrorCallback
import com.jx.ui.uimain.weight.loadCallback.LoadingCallback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.tencent.mmkv.MMKV

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }
}