package com.example.gamegallery.domain

import android.os.Parcel
import android.os.Parcelable
import org.w3c.dom.Comment
import java.net.URL
import java.text.FieldPosition
import java.util.*

data class Juego(val nombre:String?, val icon: String?, var consola : java.util.ArrayList<String>?, val genero:String?,
                 val videoUrl: String?, var comments: java.util.ArrayList<String>?, var points: List<Boolean>, var fecha_lanzamiento : Date): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            arrayListOf<Boolean>().apply {
                parcel.readArrayList(Boolean::class.java.classLoader)
            },
            Date(parcel.readLong())
            )
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(icon)
        dest?.writeStringArray(consola?.toTypedArray())
        dest?.writeString(genero)
        dest?.writeString(videoUrl)
        dest?.writeStringArray(comments?.toTypedArray())
        dest?.writeList(points)
        dest?.writeLong(fecha_lanzamiento.time)
    }

    override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR

    fun postComment(comment: String){
        //comments += comment
    }
    fun postValuation(valuation: Boolean){
        points += valuation
    }
    fun getTotalValuation(): Int {
        var retorno= 0
        points.forEach{
            if(it){
                retorno++
            }
        }
        return retorno*100/points.size
    }

    companion object CREATOR : Parcelable.Creator<Juego> {
        override fun createFromParcel(parcel: Parcel): Juego {
            return Juego(parcel)
        }

        override fun newArray(size: Int): Array<Juego?> {
            return arrayOfNulls(size)
        }
    }


}

data class Usuario(val usuario:String, val contraseña:String,val correo:String,val consolas: MutableList<String>){

}

