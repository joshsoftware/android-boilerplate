package com.amit.app.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amit.app.data.model.local.db.Student
import com.amit.app.ui.main.MainDataBindingAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("dataList")
    fun setChefAvailabilityList(recyclerView: RecyclerView, list: MutableList<Student>) {
        if (recyclerView.adapter != null) {
            val mAdapter = recyclerView.adapter as MainDataBindingAdapter
            mAdapter.updateData(list)
        } else {
            val mAdapter = MainDataBindingAdapter(recyclerView.context, list)
            recyclerView.adapter = mAdapter
        }
    }
}