package com.android.live

import android.content.Context
import android.util.AttributeSet
import com.android.base.views.BaseItemView
import com.android.live.databinding.ItemHomeTitleLayoutBinding
import com.android.live.vm.ui.TitleItemUiViewModel

/**
 * 首页标题
 */
class TitleItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    BaseItemView<ItemHomeTitleLayoutBinding, TitleItemUiViewModel>(context, attrs, defStyleAttr) {

    override fun setLayoutResId(): Int {
        return R.layout.item_home_title_layout
    }

    override fun setStyle(resId: Int) {
    }

    override fun setDataToView(data: TitleItemUiViewModel) {
        dataBinding.vm = data
    }
}