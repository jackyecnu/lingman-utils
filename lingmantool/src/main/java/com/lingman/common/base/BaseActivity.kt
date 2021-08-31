package com.lingman.common.base


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lingman.common.tool.DemonAppManager
import com.lingman.common.widget.LoadingDialog

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private lateinit var mLoadingDialog: LoadingDialog
    lateinit var mBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DemonAppManager.instance.addActivity(this)
        // 在绑定contentview 之前调用
        initDataBeforeContentView()


        mLoadingDialog = LoadingDialog(this, false)

        // 绑定contentview
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())

        //mBinding.clickHandler = this
        // 初始化控件
        initData(savedInstanceState)
        // 初始化点击事件
        initClick()
        // 绑定liveData
        initLiveData()


    }

    override fun onDestroy() {
        super.onDestroy()
        DemonAppManager.instance.removeActivity(this)
        mBinding.unbind()
    }


    // content Id
    abstract fun getLayoutId(): Int


    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    // 解决lieveData 回调
    open fun initLiveData(){}

    /**
     * 初始化点击事件
     */
    open fun initClick(){}
    /**
     * 在没有加载contentview之前调用
     */
    open fun initDataBeforeContentView(){  }

    /**
     * show 加载中
     */
    fun showLoading() {
        mLoadingDialog.showDialog(this, false)
    }

    /**
     * dismiss loading dialog
     */
    fun dismissLoading() {
        mLoadingDialog.dismissDialog()
    }

    /**
     * 得到Res下颜色
     *
     * @param color
     * @return
     */
    open fun getResColor(color: Int): Int {
        return ContextCompat.getColor(applicationContext, color)
    }

    /**
     * 设置toolbar名称
     */
    protected fun setToolbarTitle(view: TextView, title: String) {
        view.text = title
    }

    /**
     * 设置toolbar返回按键图片
     */
    protected fun setToolbarBackIcon(view: ImageView, id: Int) {
        view.setBackgroundResource(id)
    }
}