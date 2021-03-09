package com.android.live

import android.content.Context
import android.util.AttributeSet
import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.base.views.BaseItemView
import com.android.live.databinding.ItemHomeImageLayoutBinding
import com.android.live.vm.ui.ImageItemUiViewModel

class ImageItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    BaseItemView<ItemHomeImageLayoutBinding, ImageItemUiViewModel>(context, attrs, defStyleAttr) {

    override fun setLayoutResId(): Int = R.layout.item_home_image_layout

    override fun setStyle(resId: Int) {
    }

    override fun setDataToView(data: ImageItemUiViewModel) {
        dataBinding.vm = data
    }
}