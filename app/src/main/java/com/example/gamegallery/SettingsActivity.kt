package com.example.gamegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.get
import com.example.gamegallery.domain.Info
import kotlinx.android.synthetic.main.activity_settings.*
import java.lang.RuntimeException

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        for (i in 0..settingsLayout.childCount-1){
            if(settingsLayout[i] is CheckBox){
                var cb = (settingsLayout[i] as CheckBox)
                var texto = cb.text.toString()
                var checked = Info.plataformasAMostrar[texto];
                if(checked!=null)
                    cb.isChecked=checked;
                else
                    throw RuntimeException("Nombre de plataforma no encontrado")

            }
        }
    }

    override fun onPause() {
        super.onPause()
        for (i in 0..settingsLayout.childCount-1){
            if(settingsLayout[i] is CheckBox){
                var cb = (settingsLayout[i] as CheckBox)
                var texto : String = cb.text.toString()
                var checked = cb.isChecked
                Info.plataformasAMostrar[texto]=checked;
            }
        }

    }
}
