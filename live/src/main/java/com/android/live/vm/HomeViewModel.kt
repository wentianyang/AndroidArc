package com.android.live.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.live.ApiService
import com.android.live.bean.HomeBean
import com.android.live.vm.ui.ImageItemUiViewModel
import com.android.live.vm.ui.TitleItemUiViewModel
import com.android.network.YwArtApi
import com.android.network.rx.BaseObserver

class HomeViewModel : ViewModel() {

    val homeData = MutableLiveData<List<BaseItemUiViewModel>>()

    init {
        YwArtApi.createService(ApiService::class.java)
            .home()
            .compose(YwArtApi.getInstance().applyScheduler(object : BaseObserver<HomeBean>() {
                override fun onSuccess(data: HomeBean) {
                    val list = mutableListOf<BaseItemUiViewModel>()
                    val title = data.body.activityZone.title
                    val routerPath = data.body.activityZone.actionUrl
                    val titleItemUiViewModel = TitleItemUiViewModel(title, routerPath)
                    list.add(titleItemUiViewModel)
                    data.body.activityZone.items.forEach {
                        list.add(ImageItemUiViewModel(it.imgUrl, it.appUrl))
                    }
                    Log.d("HomeViewModel", "onActivityCreated: $list")
                    homeData.value = list
                }

                override fun onFailure(e: Throwable?) {
                    e?.printStackTrace()
                }
            }))
    }
}