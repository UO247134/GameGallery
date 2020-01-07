package com.example.gamegallery.ui.tabs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamegallery.R
import com.example.gamegallery.ui.SecondActivity
import com.example.gamegallery.domain.Info
import kotlinx.android.synthetic.main.tab_base.*

open class TabBase(private val nombreConsola:String) : Fragment()
{
    constructor():this("All")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.tab_base,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_base.apply{
            layoutManager= LinearLayoutManager(context)
            adapter= JuegoListAdapter(Info.getJuegos(nombreConsola))
            addOnItemTouchListener(RecyclerTouchListener(context, this, MyClickListener(nombreConsola)))
        }

    }

    class MyClickListener(val nombreConsola: String) : RecyclerTouchListener.ClickListener {
        override fun onLongClick(view: View?, position: Int) {

        }

        override fun onClick(view: View?, position: Int) {
            val intent = Intent (view?.context, SecondActivity::class.java)
            intent.putExtra("Juego",Info.getJuegos(nombreConsola)[position])
            view?.context?.startActivity(intent)
        }


    }

}