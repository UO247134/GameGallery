package com.example.gamegallery;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gamegallery.domain.Info;
import com.example.gamegallery.tabs.TabBase;

import java.io.Console;
import java.util.Map;

public class PageAdapter extends FragmentStatePagerAdapter {
    Map<Integer,String> plataformas = Info.Companion.getVisibles();



    public PageAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        String nombre = plataformas.get(position);
        if(nombre==null)
            Log.e("Error","Pos:" +position+" Plataformas" + plataformas.size());
        return new TabBase(plataformas.get(position));
    }
    @Override
    public CharSequence getPageTitle(int position){
        return plataformas.get(position);
    }

    @Override
    public int getCount() {
        return plataformas.size();
    }

}
