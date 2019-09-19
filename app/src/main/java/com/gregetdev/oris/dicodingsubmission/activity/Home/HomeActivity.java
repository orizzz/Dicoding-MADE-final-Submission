package com.gregetdev.oris.dicodingsubmission.activity.Home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.gregetdev.oris.dicodingsubmission.SettingActivity;
import com.gregetdev.oris.dicodingsubmission.fragment.FavFragment;
import com.gregetdev.oris.dicodingsubmission.fragment.MoviesFragment;
import com.gregetdev.oris.dicodingsubmission.R;
import com.gregetdev.oris.dicodingsubmission.fragment.SearchFragment;
import com.gregetdev.oris.dicodingsubmission.fragment.TvFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragmentMovie = new MoviesFragment();
    Fragment fragmentTV = new TvFragment();
    Fragment fragmentFav = new FavFragment();

    Toolbar toolbar;

    private String tab = "Movies";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation =  findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_movies:
                        setFragment(fragmentMovie);
                        tab = "Movies";
                        return true;
                    case R.id.navigation_tv:
                        setFragment(fragmentTV);
                        tab = "Tv";
                        return true;
                    case R.id.navigation_fav:
                        setFragment(fragmentFav);
                        tab = "Favorite";
                        return true;
                    default:
                        setFragment(fragmentMovie);
                        tab = "Movies";
                        return false;
                }
            }
        });

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movies);
        }
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_content,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_setting, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getResources().getString(R.string.search_bar));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Fragment fragmentSearch = SearchFragment.newInstance(query,tab);
                setFragment(fragmentSearch);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting_bar:
                Intent mIntent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(mIntent);
                return true;


        }
        return false;
    }

}
