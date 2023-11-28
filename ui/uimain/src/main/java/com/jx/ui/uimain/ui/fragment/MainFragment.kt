package com.jx.ui.uimain.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jx.ui.uimain.base.BaseFragment
import com.jx.ui.uimain.databinding.FragmentMainBinding
import com.jx.ui.uimain.viewmodel.FMainViewModel

class MainFragment : BaseFragment<FMainViewModel, FragmentMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.vm = mViewModel
        mDatabind.lifecycleOwner = this
        mDatabind.vp.adapter = object : FragmentStateAdapter(requireFragmentManager(), lifecycle) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    0 -> HomeFragment()
                    1 -> SquareFragment()
                    2 -> MsgFragment()
                    else -> MineFragment()
                }
        }
        mDatabind.vp.offscreenPageLimit = 5
        mDatabind.vp.isUserInputEnabled = false
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.vpPosition.observe(this) {
            mDatabind.vp.setCurrentItem(it, false)
        }
    }
}