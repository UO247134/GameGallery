package com.example.gamegallery.ui;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gamegallery.domain.Info;
import com.example.gamegallery.ui.tabs.TabBase;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

class PageAdapter extends FragmentStatePagerAdapter {
    private final Map<Integer,String> plataformas = Info.Companion.getVisibles();



    public PageAdapter(FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NotNull
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
