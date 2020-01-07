package com.example.gamegallery.ui

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gamegallery.R
import com.example.gamegallery.domain.Juego
import com.google.android.youtube.player.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_concrete_game.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concrete_game)
        var parcel = intent.extras?.getParcelable<Juego>("Juego")

        addTextView(linLayout,parcel?.nombre,30.0f,true)
        addImageView(linLayout,parcel?.icon)
        //addVideoView(linLayout, parcel?.videoUrl)
        var consolas="\nConsolas: \n";
        parcel?.consola?.forEachIndexed { index, consola -> consolas+=consola+" - "}
        consolas=consolas.substring(0,consolas.length-3)
        addTextView(linLayout,consolas)

        addTextView(linLayout,"\nGénero: "+parcel?.genero)
        var comentarios = "\nComentarios:\n";
        parcel?.comments?.forEach{ c -> comentarios+="-> "+c+"\n"}
        addTextView(linLayout,comentarios)

        //addTextView(linLayout,parcel?.getTotalValuation().toString())

    }

    private fun addTextView(ll : LinearLayout, text:String?,size: Float=20.0f,bold:Boolean=false){
        val tv = TextView(this)
        tv.text = text
        tv.textSize = size
        if(bold)
            tv.setTypeface(null,Typeface.BOLD)
        tv.textAlignment= View.TEXT_ALIGNMENT_CENTER
        ll.addView(tv)

    }
    private fun addTextViewWithArray(array: List<String>?): String {
        val retorno = ""

        if (array != null) {
            Log.d("TAMAÑO",array.size.toString())
            array.forEach {
                retorno.plus(it).plus("\n")
                Log.d("COMMENT",it)
            }
        }

        return retorno

    }

    private fun addVideoView(ll : LinearLayout, url:String?){
        val vv = YouTubePlayerView(this)

        ll.addView(vv)

    }
    private fun addImageView(ll : LinearLayout, icon: String?){

        Picasso.with(this).load(icon).into(gameLogo)

    }
}
