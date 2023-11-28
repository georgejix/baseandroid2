package com.jx.ui.uimain.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jx.ui.uimain.R
import com.jx.ui.uimain.data.bean.AriticleResponse
import com.jx.ui.uimain.ext.setAdapterAnimation
import com.jx.ui.uimain.util.SettingUtil

class CollectUrlAdapter(data: ArrayList<AriticleResponse>) :
    BaseQuickAdapter<AriticleResponse, BaseViewHolder>(
        R.layout.item_ariticle, data
    ) {

    private var collectAction: (item: AriticleResponse, v: View, position: Int) -> Unit =
        { _: AriticleResponse, _: View, _: Int -> }

    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(holder: BaseViewHolder, item: AriticleResponse) {
        //赋值
        item.run {
            holder.setText(R.id.item_home_author, link)
        }
        holder.getView<View>(R.id.item_home_author)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    collectAction.invoke(item, v, holder.adapterPosition)
                }
            })
    }

    fun setCollectClick(inputCollectAction: (item: AriticleResponse, v: View, position: Int) -> Unit) {
        this.collectAction = inputCollectAction
    }
}


