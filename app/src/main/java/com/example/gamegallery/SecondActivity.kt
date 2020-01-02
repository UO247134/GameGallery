package com.example.gamegallery

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import com.example.gamegallery.domain.Juego
import kotlinx.android.synthetic.main.activity_concrete_game.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concrete_game)
        var parcel = intent.extras?.getParcelable<Juego>("Juego")

        addImageView(linLayout, parcel?.icon)
        //addVideoView(linLayout, parcel?.videoUrl)
        addTextView(linLayout,parcel?.consola.toString())
        addTextView(linLayout,parcel?.nombre)
        addTextView(linLayout,parcel?.genero)
        addTextViewWithArray(linLayout,parcel?.comments)

        //addTextView(linLayout,parcel?.getTotalValuation().toString())

    }

    private fun addTextView(ll : LinearLayout, text:String?){
        val tv = TextView(this)
        tv.text = text
        tv.textSize = 40.0f
        ll.addView(tv)

    }
    private fun addTextViewWithArray(ll: LinearLayout, array: List<String>?){
        val tv = TextView(this)
        val retorno = ""
        if (array != null) {
            Log.d("TAMAÑO",array.size.toString())
        }
        if (array != null) {
            array.forEach {
                retorno.plus(it).plus("\n")
                Log.d("COMMENT",it)
            }
        }

        tv.text = retorno
        tv.textSize = 40.0f
        ll.addView(tv)

    }
    private fun addVideoView(ll : LinearLayout, url:String?){
        val vv = VideoView(this)
        vv.setVideoURI(Uri.parse(url))
        ll.addView(vv)

    }
    private fun addImageView(ll : LinearLayout, url:String?){
        val iv = ImageView(this)
        iv.setImageURI(Uri.parse(url))
        ll.addView(iv)

    }
}
