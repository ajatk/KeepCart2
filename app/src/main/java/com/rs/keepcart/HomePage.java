package com.rs.keepcart;

import android.arch.lifecycle.HolderFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rs.keepcart.fragments.Home;

public class HomePage extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
     private FragmentTransaction fragmentTransaction;
     private Fragment fragment;
     private Home homeFragment;
    NavigationView navigationView;
    private Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setNavigationIcon(R.drawable.menu);
        if (savedInstanceState == null) {

            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserve the fragment stack inside each tab
            loadHomeFrag(fragment);

        } else {
            // restoring the previously created fragment
            // and getting the reference
            homeFragment = (Home) getSupportFragmentManager().getFragments().get(0);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            fragment = new Home();
            super.onBackPressed();
        }
    }

public void loadHomeFragment(Fragment fragment)
{
    fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.frame, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
}
    public void loadHomeFrag(Fragment fragment)
    {
        if (homeFragment == null) {
            homeFragment = new Home();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame, homeFragment);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame, homeFragment);
            fragmentTransaction.commit();
        }
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home_)
        {
            //loadHomeFrag(fragment);
            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.plans_pack) {
            Toast.makeText(getApplicationContext(), "success full click------home", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.shop_extra) {

        } else if (id == R.id.bills_payment) {

        } else if (id == R.id.my_profile) {

        } else if (id == R.id.my_account) {

        }else if (id == R.id.request_shift) {

        }else if (id == R.id.vender_refer) {

        }else if (id == R.id.vender_sales) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    }



    /*public void displayMenuItems(int itemId)
    {
        switch (itemId)
        {
            case  R.id.home_:
            {
                Toast.makeText(HomePage.this, "successfull click------home", Toast.LENGTH_SHORT).show();
            }

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
*/

