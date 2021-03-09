package com.android.live.vm.ui

import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.live.HomeAdapter

/**
 * 单张图片Item
 */
class ImageItemUiViewModel(
    val imageUrl: String,
    routerPath: String? = null,
    viewType: Int = HomeAdapter.VIEW_TYPE_IMAGE
) :
    BaseItemUiViewModel(routerPath, viewType) {
}