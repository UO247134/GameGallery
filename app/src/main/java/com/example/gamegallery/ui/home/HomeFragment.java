package com.example.gamegallery.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.gamegallery.PageAdapter;
import com.example.gamegallery.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        View contenedor = (View)container.getParent();
        appBar = contenedor.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        appBar.addView(tabs);

        viewPager = view.findViewById(R.id.pagerHome);
        viewPager.setAdapter(new PageAdapter(getFragmentManager()));
        tabs.setupWithViewPager(viewPager);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }

}