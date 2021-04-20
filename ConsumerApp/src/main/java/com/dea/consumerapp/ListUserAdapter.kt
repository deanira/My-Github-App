package com.dea.consumerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dea.consumerapp.databinding.ItemRowUserBinding

class ListUserAdapter :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private val mData = ArrayList<UsersResponseItem>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(items: ArrayList<UsersResponseItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun setData(items: List<UsersResponseItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.color.blue_light)
            .centerCrop()
            .into(this)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)
        fun bind(items: UsersResponseItem) {
            binding.tvNamaUser.text = items.login
            binding.tvUsername.text = items.url
            binding.tvCompany.text = items.type
            binding.civProfilePicture.loadImage(items.avatarUrl)
            binding.tvNamaUser.text = items.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_animation)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(mData[position]) }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UsersResponseItem)
    }
}