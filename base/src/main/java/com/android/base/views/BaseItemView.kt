package com.android.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.base.recyclerview.BaseItemUiViewModel

/**
 * RecyclerView每个Item的基类
 */
abstract class BaseItemView<T : ViewDataBinding, VM : BaseItemUiViewModel> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IBaseView<VM> {

    lateinit var dataBinding: T
    lateinit var viewModel: VM
    private val layoutResId = this.setLayoutResId()

    init {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (layoutResId != 0) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutResId, this, false)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(dataBinding.root)
    }

    /**
     * 设置新数据
     */
    override fun setData(data: VM) {
        viewModel = data
        setDataToView(viewModel)
        dataBinding.executePendingBindings()
        onDataUpdated()
    }

    protected fun onDataUpdated() {

    }

    override fun getItemView(): View = dataBinding.root


    abstract fun setDataToView(data: VM)

    abstract fun setLayoutResId(): Int
}