package com.example.vertiefungqrvisitenkartenapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SocialMediaRecyclerAdapter(private val socialMediaData: ArrayList<SocialMediaData>) :
    RecyclerView.Adapter<SocialMediaRecyclerAdapter.MyViewHolder>() {

    private lateinit var userListener: OnUserClickListener;

    interface OnUserClickListener {
        fun onUserClick(position: Int)


    }

    fun setOnUserClickListener(listener: OnUserClickListener) {
        userListener = listener;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.social_media_view_design,
            parent, false
        )
        return MyViewHolder(itemView, userListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val socialAccount = socialMediaData[position]
        holder.socialMediaLink.text = socialAccount.socialMediaAccountLink


    }

    override fun getItemCount(): Int {

        return socialMediaData.size
    }


    class MyViewHolder(itemView: View, listener: OnUserClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val socialMediaLink: TextView = itemView.findViewById(R.id.userCardViewNameView)


        init {
            itemView.setOnClickListener {

                listener.onUserClick(adapterPosition);
            }
        }


    }


}
