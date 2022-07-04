package com.example.vertiefungqrvisitenkartenapp

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class UserData(
    val userFirstName: String? = "",
    val userLastName: String? = "",
    var phoneNumber: String? = "",
    val email: String? = "",
    val description: String? = "",
    var image: Bitmap? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Bitmap::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userFirstName)
        parcel.writeString(userLastName)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
        parcel.writeString(description)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }


}