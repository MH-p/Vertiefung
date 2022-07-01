package com.example.vertiefungqrvisitenkartenapp



import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class RecyclerViewAdapter(
    @NonNull options: FirebaseRecyclerOptions<UserData>
) :
    FirebaseRecyclerAdapter<UserData, RecyclerViewAdapter.UserViewholder>(options) {
    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    override fun onBindViewHolder(
        @NonNull holder: UserViewholder,
        position: Int, @NonNull model: UserData
    ) {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.firstname.setText(model.userFirstName)

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.lastname.setText(model.userLastName)


    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): UserViewholder {
        val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.user_view_design, parent, false)
        return UserViewholder(view)
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    inner class UserViewholder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var firstname: TextView
        var lastname: TextView
        var age: TextView

        init {
            firstname = itemView.findViewById(R.id.)
            lastname = itemView.findViewById(R.id.lastname)
            age = itemView.findViewById(R.id.age)
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