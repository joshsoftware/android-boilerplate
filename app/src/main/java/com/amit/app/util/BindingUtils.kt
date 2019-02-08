package com.amit.app.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amit.app.R
import com.amit.app.data.model.api.response.User
import com.amit.app.ui.main.listingwithdatabinding.MainDataBindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

object BindingUtils {

    @JvmStatic
    @BindingAdapter("dataList")
    fun setChefAvailabilityList(recyclerView: RecyclerView, list: MutableList<User>) {
        if (recyclerView.adapter != null) {
            val mAdapter = recyclerView.adapter as MainDataBindingAdapter
            mAdapter.updateData(list)
        } else {
            val mAdapter = MainDataBindingAdapter(recyclerView.context, list)
            recyclerView.adapter = mAdapter
        }
    }

    @BindingAdapter("app:loadCircularImage")
    @JvmStatic
    fun loadCircularImage(imageView: ImageView, filepath: String?) {

        Glide.with(imageView.context)
            .load(filepath)
            .apply(
                RequestOptions.bitmapTransform(CircleCrop())
                    .placeholder(R.drawable.ic_launcher_background) //Change placeholder
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_launcher_background) //Change Error placeholder
                    .timeout(900000)
            ).into(imageView)

    }
}