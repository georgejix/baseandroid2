package com.jx.ui.uimain.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jx.base.basemain.base.viewmodel.BaseViewModel
import com.jx.base.basemain.ext.request
import com.jx.ui.uimain.data.bean.AriticleResponse
import com.jx.ui.uimain.network.apiService
import com.jx.ui.uimain.network.stateCallback.ListDataUiState

class FHomeViewModel : BaseViewModel() {
    //页码 首页数据页码从0开始
    var pageNo = 0
    var homeDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    fun queryList(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ apiService.getAritrilList(pageNo) }, {
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            homeDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            homeDataState.value = listDataUiState
        })
    }
}