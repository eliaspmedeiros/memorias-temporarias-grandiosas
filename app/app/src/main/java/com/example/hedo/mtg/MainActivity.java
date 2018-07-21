package com.example.hedo.mtg;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hedo.mtg.fragments.ColorFragment;
import com.example.hedo.mtg.models.Color;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private static final String BLACK_TAG = "Black",
            BLUE_TAG = "Blue",
            GREEN_TAG = "Green",
            RED_TAG = "Red",
            WHITE_TAG = "White",
            FAVORITES_TAG = "Favorites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        configureToolbar();
        configureNavigationDrawer();
        show(BLACK_TAG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        String tag = BLACK_TAG;

        switch (item.getItemId()) {
            case R.id.nav_black:
                tag = BLACK_TAG;
                break;
            case R.id.nav_blue:
                tag = BLUE_TAG;
                break;
            case R.id.nav_green:
                tag = GREEN_TAG;
                break;
            case R.id.nav_red:
                tag = RED_TAG;
                break;
            case R.id.nav_white:
                tag = WHITE_TAG;
                break;
            case R.id.nav_fav:
                tag = FAVORITES_TAG;
                break;
        }

        mDrawerLayout.closeDrawers();
        show(tag);

        return true;
    }

    private void show(String fragmentTag) {
        FragmentManager manager = getSupportFragmentManager();
        ColorFragment fragment = (ColorFragment) manager.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            Color fragmentColor = Color.BLACK;

            switch (fragmentTag) {
                case BLACK_TAG:
                    fragmentColor = Color.BLACK;
                    break;
                case BLUE_TAG:
                    fragmentColor = Color.BLUE;
                    break;
                case GREEN_TAG:
                    fragmentColor = Color.GREEN;
                    break;
                case RED_TAG:
                    fragmentColor = Color.RED;
                    break;
                case WHITE_TAG:
                    fragmentColor = Color.WHITE;
                    break;
                case FAVORITES_TAG:
                    fragmentColor = Color.FAVORITES;
                    break;
            }

            fragment = ColorFragment.newInstance(fragmentColor);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment, fragmentTag);
            setTitle(fragmentTag);
            transaction.commit();
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    private void configureNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }
}
