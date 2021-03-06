package com.amit.ui.main.normallisting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.app.R
import com.amit.app.databinding.ItemRecyclerListBinding
import com.amit.data.model.api.response.User

class MainSimpleAdapter(val dataList: List<User>) :
    RecyclerView.Adapter<MainSimpleAdapter.ViewHolder>() {
    private lateinit var mItemListRecycler: ItemRecyclerListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mItemListRecycler = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_recycler_list,
            parent, false
        )
        return ViewHolder(mItemListRecycler.root)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        mItemListRecycler.dataModel = dataList[position]
//        mItemListRecycler.dataModel = dataList[position]
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!.rootView)
}