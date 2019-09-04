package io.github.apmwar.classifile;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Toolbar searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchbar = (Toolbar)findViewById(R.id.searchbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        searchbar.setTitle("");
        getSupportActionBar().setElevation(10);

        BottomNavigationView nav = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        Fragment fragment = new GalleryFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                if(menuItem.getItemId() == R.id.nav_gallery) {
                    fragment = new GalleryFragment();
                    setSupportActionBar(toolbar);
                } else if(menuItem.getItemId() == R.id.nav_list) {
                    fragment = new ListFragment();
                    setSupportActionBar(toolbar);
                } else if(menuItem.getItemId() == R.id.nav_categories) {
                    fragment = new CategoriesFragment();
                    setSupportActionBar(toolbar);
                } else if(menuItem.getItemId() == R.id.nav_search) {
                    fragment = new SearchFragment();
                    setSupportActionBar(searchbar);
                }
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();

                getSupportActionBar().setElevation(10);

                return true;
            }
        });
    }
}
