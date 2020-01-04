package com.example.gamegallery.domain

import android.os.Parcel
import android.os.Parcelable
import org.w3c.dom.Comment
import java.net.URL
import java.text.FieldPosition

data class Juego(val nombre:String?, val icon: String?, val consola : List<String>, val genero:String?,
                 val videoUrl: String?, var comments: List<String>, var points: List<Boolean>): Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            arrayListOf<String>().apply {
                parcel.readArrayList(String::class.java.classLoader)
            },
            parcel.readString(),
            parcel.readString(),
            arrayListOf<String>().apply {
                parcel.readArrayList(String::class.java.classLoader)
            },
            arrayListOf<Boolean>().apply {
                parcel.readArrayList(Boolean::class.java.classLoader)
            }
            )
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(icon)
        dest?.writeList(consola)
        dest?.writeString(genero)
        dest?.writeString(videoUrl)
        dest?.writeList(comments)
        dest?.writeList(points)

    }

    override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR

    fun postComment(comment: String){
        comments += comment
    }
    fun postValuation(valuation: Boolean){
        points += valuation
    }
    fun getTotalValuation(): Int {
        var retorno= 0
        points.forEach{
            if(it){
                retorno++;
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

