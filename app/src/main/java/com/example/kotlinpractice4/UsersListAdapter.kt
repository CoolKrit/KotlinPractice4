package com.example.kotlinpractice4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsersListAdapter(private val productsList: List<User>) :
    RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productsList[position]
        holder.idTextView.text = currentItem.id.toString()
        holder.titleTextView.text = currentItem.firstName
        holder.priceTextView.text = currentItem.age.toString()
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.id)
        val titleTextView: TextView = view.findViewById(R.id.firstName)
        val priceTextView: TextView = view.findViewById(R.id.age)
    }
}
