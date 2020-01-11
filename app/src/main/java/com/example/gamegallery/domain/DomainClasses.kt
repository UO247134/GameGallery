package com.example.gamegallery.domain

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Juego(val nombre:String?, val icon: String?, var consola : ArrayList<String>?, val genero:String?,
                 val videoUrl: String?, var comments: ArrayList<String>?, var points: Int, var fecha_lanzamiento : Date,var id: String?): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readInt(),
            Date(parcel.readLong()),
            parcel.readString()
            )
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(icon)
        dest?.writeStringArray(consola?.toTypedArray())
        dest?.writeString(genero)
        dest?.writeString(videoUrl)
        dest?.writeStringArray(comments?.toTypedArray())
        dest?.writeInt(points)
        dest?.writeLong(fecha_lanzamiento.time)
        dest?.writeString(id)
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

data class Usuario(val usuario:String, val contrasena:String, val correo:String, val consolas: MutableList<String>)

