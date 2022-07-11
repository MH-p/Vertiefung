package com.example.vertiefungqrvisitenkartenapp

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class UserRecyclerViewAdapter(private val userList: ArrayList<UserData>) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.MyViewHolder>() {
    private lateinit var userListener: OnUserClickListener

    interface OnUserClickListener {
        fun onUserClick(position: Int)
    }

    fun setOnUserClickListener(listener: OnUserClickListener) {
        userListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_view_design,
            parent, false
        )
        return MyViewHolder(itemView, userListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        val fullUserName = currentUser.userFirstName + " " + currentUser.userLastName

        holder.userName.text = fullUserName
        holder.email.text = currentUser.email
        holder.phoneNumber.text = currentUser.phoneNumber

        val storage = currentUser.phoneNumber?.let {
            FirebaseStorage.getInstance("gs://vertiefungfhws.appspot.com/")
                .getReference(it)
        }

        val localFile = File.createTempFile("temp", "jpg")

        storage?.getFile(localFile)?.addOnSuccessListener {
            val profileImage = BitmapFactory.decodeFile(localFile.absolutePath)
            holder.userProfileImage.setImageBitmap(profileImage)
        }?.addOnFailureListener {
            throw IllegalAccessException()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View, listener: OnUserClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userCardViewNameView)
        val email: TextView = itemView.findViewById(R.id.userCardViewEmailView)
        val phoneNumber: TextView = itemView.findViewById(R.id.userCardViewPhoneNumberView)
        val userProfileImage: ImageView = itemView.findViewById(R.id.userCardViewUserPictureView)

        init {
            itemView.setOnClickListener {
                listener.onUserClick(absoluteAdapterPosition)
            }
        }
    }
}


