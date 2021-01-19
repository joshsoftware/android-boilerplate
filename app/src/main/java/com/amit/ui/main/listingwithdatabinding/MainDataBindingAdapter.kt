package com.amit.ui.main.listingwithdatabinding

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amit.app.R
import com.amit.ui.base.RecyclerBaseAdapter
import com.amit.ui.base.RecyclerViewHolder
import com.amit.data.model.api.response.User

class MainDataBindingAdapter(context: Context, list: MutableList<User>) : RecyclerBaseAdapter() {
    var clickedPosition = MutableLiveData<Int>()
    var mDataList: MutableList<User> = ArrayList()
    private var mContext: Context? = null

    init {
        this.mDataList = list
        this.mContext = context
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_recycler_list
    }

    override fun getDataModel(position: Int): Any? = mDataList[position]

    override fun <T> updateData(mData: List<T>) {
        val data: Collection<User> = mData as Collection<User>
        notifyDataSetChanged()
    }

    fun getClickedItemPosition(): LiveData<Int> {
        return clickedPosition
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.setOnClickListener {
            //Play chef profile training video from final step
            clickedPosition.postValue(position)
        }
    }

    override fun getItemCount(): Int {
        if (mDataList != null && mDataList?.size!! > 0) {
            return mDataList?.size!!
        } else {
            return 0
        }
    }

}