package com.dea.mygithubapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.mygithubapp.R
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.databinding.ItemSearchResultBinding

class SearchResultAdapter :
    RecyclerView.Adapter<SearchResultAdapter.ListViewHolder>() {

    private val mData = ArrayList<UsersResponseItem>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(items: ArrayList<UsersResponseItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSearchResultBinding.bind(itemView)
        fun bind(items: UsersResponseItem) {
            with(itemView) {
                binding.tvUsername.text = items.login
                Glide.with(itemView.context)
                    .load(items.avatarUrl)
                    .into(binding.civProfilePicture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(mData[position]) }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersResponseItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}