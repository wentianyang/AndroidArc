package com.android.live.vm.ui

import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.live.HomeAdapter

class TitleItemUiViewModel(
    val title: String = "标题",
    routerPath: String? = null,
    viewType: Int = HomeAdapter.VIEW_TYPE_TITLE
) : BaseItemUiViewModel(routerPath, viewType) {
}