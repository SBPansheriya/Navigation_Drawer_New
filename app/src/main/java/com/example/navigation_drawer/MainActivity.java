package com.example.navigation_drawer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer.databinding.ActivityMainBinding;
import com.example.navigation_drawer.ui.gallery.GalleryFragment;
import com.example.navigation_drawer.ui.home.HomeFragment;
import com.example.navigation_drawer.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);

        addFragment(new Fragment());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,binding.drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    addFragment(new HomeFragment());
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if(item.getItemId() == R.id.nav_gallery){
                    addFragment(new GalleryFragment());
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if(item.getItemId() == R.id.nav_slideshow){
                    addFragment(new SlideshowFragment());
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);
                }
                return true;
            }
        });
    }

    private void addFragment(Fragment Fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main,Fragment);
        transaction.commit();
    }
}