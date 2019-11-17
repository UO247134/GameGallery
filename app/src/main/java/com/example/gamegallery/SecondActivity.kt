package com.example.gamegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gamegallery.domain.Juego
import kotlinx.android.synthetic.main.activity_second.*
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var parcel = intent.extras?.getParcelable<Juego>("Juego")


        addTextView(linLayout,parcel?.consola)
        addTextView(linLayout,parcel?.nombre)

    }

    private fun addTextView(ll : LinearLayout, text:String?){
        val tv = TextView(this)
        tv.text = text
        tv.textSize = 40.0f
        ll.addView(tv)

    }
}
