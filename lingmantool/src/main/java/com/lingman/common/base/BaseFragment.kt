package com.lingman.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService

import com.lingman.common.widget.LoadingDialog


private const val TAG = "BaseFragment"

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var mBinding: T
    protected lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 在绑定contentview 之前调用
        initDataBeforeContentView(view)

        // 绑定liveData
        initLiveData()

        //绑定数据
        initData(view)

        // 初始化点击事件
        initClick()
    }

    abstract fun initData(view:View)

    // 解决lieveData 回调
    open fun initLiveData(){}

    /**
     * 初始化点击事件
     */
    open fun initClick(){}
    /**
     * 在没有加载contentview之前调用
     */
    open fun initDataBeforeContentView(view:View){  }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    abstract fun getLayoutId(): Int

}