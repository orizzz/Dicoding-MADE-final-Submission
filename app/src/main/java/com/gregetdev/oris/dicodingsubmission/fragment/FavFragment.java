package com.gregetdev.oris.dicodingsubmission.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.fragment.favorite.FavMovieFragment;
import com.gregetdev.oris.dicodingsubmission.fragment.favorite.FavTvFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavFragment extends Fragment {


    Fragment fragmentFavMovie = new FavMovieFragment();
    Fragment fragmentFavTV = new FavTvFragment();

    public FavFragment() {    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout favTab = view.findViewById(R.id.Fav_tabLayout);
        favTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    setFragment(fragmentFavMovie);
                } else if (tab.getPosition() == 1) {
                    setFragment(fragmentFavTV);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (savedInstanceState == null) {
            TabLayout.Tab tab = favTab.getTabAt(1);
            if (tab != null) {
                tab.select();
            }
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fav_content,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
