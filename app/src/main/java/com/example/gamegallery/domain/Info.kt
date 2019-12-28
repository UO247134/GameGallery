package com.example.gamegallery.domain

import com.example.gamegallery.datos.Datos

class Info {

    var juegos:List<Juego> = ArrayList<Juego>()

    fun getJuegos(nombreConsola:String):List<Juego>{
        if(juegos.size==0){
            Datos.getAllJuegos(this)
            Thread.sleep(1000)
        }
        if (nombreConsola.equals("All"))
            return ArrayList<Juego>(juegos)

        var toRet = ArrayList<Juego>()
        for( juego in juegos ){
            if(juego.consola==nombreConsola)
                toRet.add(juego)

        }
        return toRet
    }

    fun updateJuegos(juegos:List<Juego>){
        this.juegos=juegos
    }




}