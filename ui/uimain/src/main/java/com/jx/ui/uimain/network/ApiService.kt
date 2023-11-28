package com.jx.ui.uimain.network

import com.jx.ui.uimain.data.bean.ApiPagerResponse
import com.jx.ui.uimain.data.bean.ApiResponse
import com.jx.ui.uimain.data.bean.AriticleResponse
import retrofit2.http.*

/**
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 广场列表数据
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareData(@Path("page") page: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getAritrilList(@Path("page") pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>
}