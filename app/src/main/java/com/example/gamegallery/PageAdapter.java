package com.example.gamegallery;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gamegallery.tabs.TabAllPlataforms;
import com.example.gamegallery.tabs.TabPc;
import com.example.gamegallery.tabs.TabPs4;
import com.example.gamegallery.tabs.TabSwitch;
import com.example.gamegallery.tabs.TabThirdParty;
import com.example.gamegallery.tabs.TabXboxOne;

public class PageAdapter extends FragmentStatePagerAdapter {
    public int counttab;
    String[] tituloTabs={"AllGames","3rdParty","PC","PS4","Xbox","Switch"};

    public PageAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TabAllPlataforms();
            case 1:
                return new TabThirdParty();
            case 2:
                return new TabPc();
            case 3:
                return new TabPs4();
            case 4:
                return new TabXboxOne();
            case 5:
                return new TabSwitch();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return tituloTabs[position];
    }

    @Override
    public int getCount() {
        return tituloTabs.length;
    }

}
