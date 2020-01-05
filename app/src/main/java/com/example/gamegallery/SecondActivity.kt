package com.example.gamegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import com.example.gamegallery.domain.Juego
import com.google.android.youtube.player.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_concrete_game.*
import org.jetbrains.anko.find

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concrete_game)
        var parcel = intent.extras?.getParcelable<Juego>("Juego")

        addImageView(linLayout,parcel?.icon)
        //addVideoView(linLayout, parcel?.videoUrl)
        addTextView(linLayout,parcel?.consola.toString())
        addTextView(linLayout,parcel?.nombre)
        addTextView(linLayout,parcel?.genero)
        addTextView(linLayout,parcel?.comments.toString())

        //addTextView(linLayout,parcel?.getTotalValuation().toString())

    }

    private fun addTextView(ll : LinearLayout, text:String?){
        val tv = TextView(this)
        tv.text = text
        tv.textSize = 40.0f
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
        var image = ll.find<ImageView>(R.id.gameLogo)
        Picasso.with(this).load(icon).fit().into(image)

    }
}
