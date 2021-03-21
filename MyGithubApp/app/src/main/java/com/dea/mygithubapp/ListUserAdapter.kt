package com.dea.mygithubapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView


class ListUserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_nama_user)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        var tvCompany: TextView = itemView.findViewById(R.id.tv_company)
        var imgUser: CircleImageView = itemView.findViewById(R.id.civ_profile_picture)
        var itemList: CardView = itemView.findViewById(R.id.item_row_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user: User = listUser[position]

        holder.itemList.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_animation)

        holder.imgUser.setImageResource(user.avatar)
        holder.tvName.text = user.name
        holder.tvUsername.text = StringBuilder("@${user.username}")
        holder.tvCompany.text = user.company

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(user) }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}