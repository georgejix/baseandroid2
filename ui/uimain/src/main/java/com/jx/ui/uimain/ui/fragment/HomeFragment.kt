package com.jx.ui.uimain.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jx.ui.uimain.R
import com.jx.ui.uimain.base.BaseFragment
import com.jx.ui.uimain.databinding.FragmentHomeBinding
import com.jx.ui.uimain.ext.init
import com.jx.ui.uimain.ext.initFloatBtn
import com.jx.ui.uimain.ext.initFooter
import com.jx.ui.uimain.ext.loadListData
import com.jx.ui.uimain.ext.loadServiceInit
import com.jx.ui.uimain.ext.showLoading
import com.jx.ui.uimain.ui.adapter.AriticleAdapter
import com.jx.ui.uimain.view.DefineLoadMoreView
import com.jx.ui.uimain.viewmodel.FHomeViewModel
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView

class HomeFragment : BaseFragment<FHomeViewModel, FragmentHomeBinding>() {
    //适配器
    private val articleAdapter: AriticleAdapter by lazy { AriticleAdapter(arrayListOf(), true) }

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    //recyclerview的底部加载view 因为在首页要动态改变他的颜色，所以加了他这个字段
    private lateinit var footView: DefineLoadMoreView

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(mDatabind.includeLayout.swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            mViewModel.queryList(true)
        }
        //初始化recyclerView
        mDatabind.includeLayout.recyclerView.init(LinearLayoutManager(context), articleAdapter)
            .let {
                it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                footView = it.initFooter(SwipeRecyclerView.LoadMoreListener {
                    mViewModel.queryList(false)
                })
                //初始化FloatingActionButton
                it.initFloatBtn(mDatabind.floatbtn)
            }
        //初始化 SwipeRefreshLayout
        mDatabind.includeLayout.swipeRefresh.init {
            //触发刷新监听时请求数据
            mViewModel.queryList(true)
        }
        articleAdapter.run {
            setCollectClick { item, v, _ ->
            }
            setOnItemClickListener { adapter, view, position ->
                /* nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                     putParcelable(
                         "ariticleData",
                         articleAdapter.data[position - this@HomeFragment.mViewBind.includeList.includeRecyclerview.recyclerView.headerCount]
                     )
                 })*/
                Toast.makeText(context, "click item", Toast.LENGTH_SHORT).show()
            }
            addChildClickViewIds(R.id.item_home_author, R.id.item_project_author)
            setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.item_home_author, R.id.item_project_author -> {
                        Toast.makeText(context, "click author", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        //设置界面 加载中
        loadsir.showLoading()
        //请求文章列表数据
        mViewModel.queryList(true)
    }

    override fun createObserver() {
        super.createObserver()
        //监听首页文章列表请求的数据变化
        mViewModel.run {
            homeDataState.observe(viewLifecycleOwner, Observer {
                //设值 新写了个拓展函数，搞死了这个恶心的重复代码
                loadListData(
                    it,
                    articleAdapter,
                    loadsir,
                    mDatabind.includeLayout.recyclerView,
                    mDatabind.includeLayout.swipeRefresh
                )
            })
        }
    }
}