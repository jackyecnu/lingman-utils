package com.lingman.common.utils

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import coil.load
import com.lingman.common.R





    /**
     * 加载网络图片
     */
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageUrl: String?) {
        if (!TextUtils.isEmpty(imageUrl)) {

            imageView.load(imageUrl){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        } else {
            imageView.setBackgroundColor(Color.DKGRAY)
        }
    }

    /**
     * 加载资源文件中的图片
     */
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageResource: Int) {
        imageView.setImageResource(imageResource)
    }

    /**
     * 加载网络图片，多个参数的情况
     */
    @BindingAdapter(value = ["image", "defaultImageResource"], requireAll = false)
    fun setImage(imageView: ImageView, imageUrl: String?, imageResource: Int) {
        if (!TextUtils.isEmpty(imageUrl)) {
            imageView.load(imageUrl){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        } else {
            imageView.setImageResource(imageResource)
        }
    }