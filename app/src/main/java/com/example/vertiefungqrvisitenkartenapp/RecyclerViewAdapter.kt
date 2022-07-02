package com.example.vertiefungqrvisitenkartenapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var userListener: OnUserClickListener;

    interface OnUserClickListener {
        fun onUserClick(position: Int)


    }

    fun setOnUserClickListener(listener: OnUserClickListener) {
        userListener = listener;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_view_design,
            parent, false
        )
        return MyViewHolder(itemView,userListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.firstName.text = currentitem.userFirstName
//        holder.lastName.text = currentitem.userLastName
        holder.email.text = currentitem.email


    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView: View, listener: OnUserClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.nameView)
        val email: TextView = itemView.findViewById(R.id.emailView)

        init {
            itemView.setOnClickListener {

                listener.onUserClick(adapterPosition);
            }
        }


    }

}


//class RecyclerViewAdapter(private val userList: List<UserData>) :
//    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//
//    private lateinit var userListener: OnUserClickListener;
//
//    interface OnUserClickListener {
//        fun onUserClick(position: Int)
//
//
//    }
//
//    fun setOnUserClickListener(listener: OnUserClickListener) {
//        userListener = listener;
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.user_view_design, parent, false)
//        return ViewHolder(view, userListener)
//
//    }
//
//    // binds the list items to a view
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val itemsViewModel = userList[position]
//
//
////         sets the image to the imageview from our itemHolder class
////        holder.userPictureView.setImageResource(itemsViewModel.image)
//
//        // sets the text to the textview from our itemHolder class
//        holder.nameView.text = itemsViewModel.userFirstName
//        holder.emailView.text = itemsViewModel.email
//        holder.phoneNumberView.text = itemsViewModel.phoneNumber
//
//
//    }
//
//
//    // return the number of the items in the list
//    override fun getItemCount(): Int {
//        return userList.size
//    }
//
//    // Holds the views for adding it to image and text
//    class ViewHolder(ItemView: View, listener: OnUserClickListener) :
//        RecyclerView.ViewHolder(ItemView) {
//        val userPictureView: ImageView = itemView.findViewById(R.id.userPictureView)
//        val nameView: TextView = itemView.findViewById(R.id.nameView)
//        val emailView: TextView = itemView.findViewById(R.id.emailView)
//        val phoneNumberView: TextView = itemView.findViewById(R.id.phoneNumberView)
//        val userViewLayout: CardView = itemView.findViewById(R.id.userViewLayout)
//
//        init {
//            itemView.setOnClickListener {
//
//                listener.onUserClick(adapterPosition);
//            }
//        }
//    }
//}