package com.example.navigatordrawer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navigatordrawer.fragment.FirstFragment;
import com.example.navigatordrawer.fragment.FirstFragment.OnFragmentFirstListener;
import com.example.navigatordrawer.fragment.SecondFragment;
import com.example.navigatordrawer.fragment.SecondFragment.OnFragmentSecondListener;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener,
        OnFragmentFirstListener, OnFragmentSecondListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirstFragment fragment = new FirstFragment();
        fragment.setMessage("Aguardando impeachment");
        showFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;
        boolean tchauQuerida = true;

        switch (item.getItemId()) {
            case R.id.nav_camera:
                tchauQuerida = false;
                FirstFragment fragmant1 = new FirstFragment();
                fragmant1.setMessage("LULAA!");
                showFragment(fragmant1);
                break;
            case R.id.nav_gallery:
                tchauQuerida = false;
                SecondFragment fragmant2 = new SecondFragment();
                fragmant2.setMessage("TCHAU QUERIDA!");
                showFragment(fragmant2);
                break;
            case R.id.nav_slideshow:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_manage:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_share:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_send:
                fragmentClass = SecondFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
                break;
        }

        try {
            if (fragment == null && tchauQuerida) {
                fragment = (Fragment) fragmentClass.newInstance();
                showFragment(fragment);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragments_here, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onGetValue(String value) {
        Log.i("LULAA", value);
    }

    @Override
    public void onHelloFragment(String string) {

    }
}
