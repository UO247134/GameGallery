package com.example.gamegallery.datos

import com.example.gamegallery.domain.Juego

class Datos (){



    companion object {
        fun getJuegos(nombreConsola:String) : List<Juego>{
            val juegos : List<Juego> = listOf(Juego("PES 2020","https://sm.ign.com/ign_es/screenshot/default/blob_hags.png","PS4"),
                    Juego("Dark Souls 3","https://i.blogs.es/984dc1/cc76dfed12ccb88d8abcc327922f3c15/450_1000.png","3rd Party"))
            if(nombreConsola.equals("All"))
                return juegos
            var toRet = juegos.filter { juego -> juego.consola.equals(nombreConsola) }
            return toRet
        }
    }
}