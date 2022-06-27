package com.example.vertiefungqrvisitenkartenapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private val mList: List<UserViewModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.userPictureView.setImageResource(itemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.nameView.text = itemsViewModel.userName
        holder.emailView.text = itemsViewModel.email
        holder.phoneNumberView.text = itemsViewModel.phoneNumber


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val userPictureView: ImageView = itemView.findViewById(R.id.userPictureView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val emailView: TextView = itemView.findViewById(R.id.emailView)
        val phoneNumberView: TextView = itemView.findViewById(R.id.phoneNumberView)
    }
}