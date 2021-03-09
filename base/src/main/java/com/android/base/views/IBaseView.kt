package com.android.base.views

import android.view.View
import com.android.base.recyclerview.BaseItemUiViewModel

interface IBaseView<VM : BaseItemUiViewModel> {

    /**
     * 设置新数据
     */
    fun setData(data: VM)

    /**
     * 获取ItemView
     */
    fun getItemView(): View

    fun setStyle(resId: Int)
}