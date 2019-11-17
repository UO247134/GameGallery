package com.example.gamegallery.datos

import com.example.gamegallery.domain.Juego

class Datos (){



    companion object {
        fun getJuegos(nombreConsola:String) : List<Juego>{
            val juegos : List<Juego> = listOf(Juego("PES 2020","@drawable/ic_menu_home","PS4"),Juego("Cities: Skylines"," @drawable/ic_menu-home","PC"))
            if(nombreConsola.equals("All"))
                return juegos
            var toRet = juegos.filter { juego -> juego.consola.equals(nombreConsola) }
            return toRet
        }
    }
}