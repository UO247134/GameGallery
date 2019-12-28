package com.example.gamegallery;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gamegallery.domain.Info;
import com.example.gamegallery.tabs.TabBase;

public class PageAdapter extends FragmentStatePagerAdapter {
    public int counttab;
    String[] tituloTabs={"AllGames","3rdParty","PC","PS4","Xbox","Switch"};
    Info info = new Info();

    public PageAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TabBase("All",info);
            case 1:
                return new TabBase("3rd Party",info);
            case 2:
                return new TabBase("PC",info);
            case 3:
                return new TabBase("PS4",info);
            case 4:
                return new TabBase("Xbox",info);
            case 5:
                return new TabBase("Switch",info);
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
