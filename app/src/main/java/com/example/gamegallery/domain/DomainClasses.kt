package com.example.gamegallery.domain

import android.os.Parcel
import android.os.Parcelable
import java.net.URL
import java.text.FieldPosition

data class Juego(val nombre:String?, val icon: String?, val consola : String?, val genero:String?): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
            ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(icon)
        dest?.writeString(consola)
        dest?.writeString(genero)
    }

    override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR

    companion object CREATOR : Parcelable.Creator<Juego> {
        override fun createFromParcel(parcel: Parcel): Juego {
            return Juego(parcel)
        }

        override fun newArray(size: Int): Array<Juego?> {
            return arrayOfNulls(size)
        }
    }


}

