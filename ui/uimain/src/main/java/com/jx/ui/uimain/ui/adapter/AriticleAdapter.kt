package com.jx.ui.uimain.ui.adapter

import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jx.base.basemain.ext.util.toHtml
import com.jx.ui.uimain.R
import com.jx.ui.uimain.data.bean.AriticleResponse
import com.jx.ui.uimain.ext.setAdapterAnimation
import com.jx.ui.uimain.util.SettingUtil

class AriticleAdapter(data: MutableList<AriticleResponse>?) :
    BaseDelegateMultiAdapter<AriticleResponse, BaseViewHolder>(data) {
    private val Ariticle = 1//文章类型
    private val Project = 2//项目类型 本来打算不区分文章和项目布局用统一布局的，但是布局完以后发现差异化蛮大的，所以还是分开吧
    private var showTag = false//是否展示标签 tag 一般主页才用的到

    private var collectAction: (item: AriticleResponse, v: View, position: Int) -> Unit =
        { _: AriticleResponse, _: View, _: Int -> }

    constructor(data: MutableList<AriticleResponse>?, showTag: Boolean) : this(data) {
        this.showTag = showTag
    }

    init {
        setAdapterAnimation(SettingUtil.getListMode())
        // 第一步，设置代理
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<AriticleResponse>() {
            override fun getItemType(data: List<AriticleResponse>, position: Int): Int {
                //根据是否有图片 判断为文章还是项目，好像有点low的感觉。。。我看实体类好像没有相关的字段，就用了这个，也有可能是我没发现
                return if (TextUtils.isEmpty(data[position].envelopePic)) Ariticle else Project
            }
        })
        // 第二步，绑定 item 类型
        getMultiTypeDelegate()?.let {
            it.addItemType(Ariticle, R.layout.item_ariticle)
            it.addItemType(Project, R.layout.item_project)
        }
    }

    override fun convert(helper: BaseViewHolder, item: AriticleResponse) {
        when (helper.itemViewType) {
            Ariticle -> {
                //文章布局的赋值
                item.run {
                    helper.setText(
                        R.id.item_home_author,
                        if (author.isNotEmpty()) author else shareUser
                    )
                    helper.setText(R.id.item_home_content, title.toHtml())
                    helper.setText(R.id.item_home_type2, "$superChapterName·$chapterName".toHtml())
                    helper.setText(R.id.item_home_date, niceDate)
                }
            }

            Project -> {
                //项目布局的赋值
                item.run {
                    helper.setText(
                        R.id.item_project_author,
                        if (author.isNotEmpty()) author else shareUser
                    )
                    helper.setText(R.id.item_project_title, title.toHtml())
                    helper.setText(R.id.item_project_content, desc.toHtml())
                    helper.setText(
                        R.id.item_project_type,
                        "$superChapterName·$chapterName".toHtml()
                    )
                    helper.setText(R.id.item_project_date, niceDate)
                    Glide.with(context).load(envelopePic)
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .into(helper.getView(R.id.item_project_imageview))
                }
            }
        }
    }

    fun setCollectClick(inputCollectAction: (item: AriticleResponse, v: View, position: Int) -> Unit) {
        this.collectAction = inputCollectAction
    }

}


