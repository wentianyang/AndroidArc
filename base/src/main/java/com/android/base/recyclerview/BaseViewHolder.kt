package com.android.base.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.android.base.views.IBaseView

/**
 * RecyclerView ViewHolder 基类
 * @param VM item的数据类
 */
open class BaseViewHolder<VM : BaseItemUiViewModel>(private val rootView: IBaseView<VM>) :
    RecyclerView.ViewHolder(rootView.getItemView()) {

    fun onBind(vm: VM) {
        vm.javaClass
        rootView.setData(vm)
    }
}