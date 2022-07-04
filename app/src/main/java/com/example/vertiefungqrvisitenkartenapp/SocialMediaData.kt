package com.example.vertiefungqrvisitenkartenapp

import android.os.Parcel
import android.os.Parcelable

data class SocialMediaData( val socialMediaAccountLink: String? = "") : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(socialMediaAccountLink)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SocialMediaData> {
        override fun createFromParcel(parcel: Parcel): SocialMediaData {
            return SocialMediaData(parcel)
        }

        override fun newArray(size: Int): Array<SocialMediaData?> {
            return arrayOfNulls(size)
        }
    }

}