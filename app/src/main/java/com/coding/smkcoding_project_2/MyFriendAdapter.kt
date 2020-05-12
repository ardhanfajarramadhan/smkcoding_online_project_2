package com.coding.smkcoding_project_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_my_friend_item.*

class MyFriendAdapter(private val context:Context, private val items:ArrayList<MyFriend>):
    RecyclerView.Adapter<MyFriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.layout_my_friend_item, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyFriendAdapter.ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }

    class ViewHolder(override val containerView : View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item : MyFriend) {
            tvFriendName.text = item.nama
            tvFriendEmail.text = item.email
            tvFriendTelp.text = item.telp
        }
    }
}