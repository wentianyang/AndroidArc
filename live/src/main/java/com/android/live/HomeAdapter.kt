package com.android.live

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.base.recyclerview.BaseViewHolder

class HomeAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    val TAG = "HomeAdapter"

    var data = mutableListOf<BaseItemUiViewModel>()
        set(value) {
            field = value
            Log.d(TAG, "set new data : ${value.size}")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_TYPE_TITLE -> {
                BaseViewHolder(TitleItemView(context))
            }
            VIEW_TYPE_IMAGE -> {
                BaseViewHolder(ImageItemView(context))
            }
            else -> {
                BaseViewHolder(TitleItemView(context))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as BaseViewHolder<BaseItemUiViewModel>)
        val itemData = data[position]
        Log.d(TAG, "onBindViewHolder--------------: ${itemData.javaClass}")
        holder.onBind(itemData)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    fun addData(d: BaseItemUiViewModel) {
        data.add(d)
        notifyItemChanged(data.size - 1)
    }

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_IMAGE = 1
        const val VIEW_TYPE_IMAGE_TEXT = 2
    }
}