package com.amit.app.ui.main.normallisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.app.R
import com.amit.app.data.model.api.response.User
import com.amit.app.databinding.ItemRecyclerListItemBinding

class MainSimpleAdapter(val dataList: List<User>) : RecyclerView.Adapter<MainSimpleAdapter.ViewHolder>() {
    private lateinit var mItemListRecycler: ItemRecyclerListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mItemListRecycler = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_recycler_list_item,
            parent, false
        )
        return ViewHolder(mItemListRecycler.root)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        mItemListRecycler.dataModel = dataList[position]
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!.rootView)
}