package com.example.gamegallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.core.view.get
import com.example.gamegallery.R
import com.example.gamegallery.domain.Info
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        for (i in 0 until settingsLayout.childCount){
            if(settingsLayout[i] is CheckBox){
                val cb = (settingsLayout[i] as CheckBox)
                val texto = cb.text.toString()
                val checked = Info.usuarioActual.consolas.contains(texto)
                cb.isChecked=checked


            }
        }
    }

    override fun onPause() {
        super.onPause()
        for (i in 0 until settingsLayout.childCount){
            if(settingsLayout[i] is CheckBox){
                val cb = (settingsLayout[i] as CheckBox)
                val texto : String = cb.text.toString()
                val checked = cb.isChecked
                if(checked && !Info.usuarioActual.consolas.contains(texto))
                    Info.usuarioActual.consolas.add(texto)
                if(!checked && Info.usuarioActual.consolas.contains(texto))
                    Info.usuarioActual.consolas.remove(texto)

            }
        }

    }
}
