package com.rs.keepcart.userScreens.userDashBoard;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.rs.keepcart.R;
import com.rs.keepcart.dashboard.HomeFragment;
import com.rs.keepcart.databinding.ActivityUserHomeBinding;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.fragments.RequestToShift;
import com.rs.keepcart.salesReport.SalesReportFragment;
import com.rs.keepcart.wallet.VendorWalletActivity;

public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private HomeFragment homeFragment;
    NavigationView navigationView;
    private Context context = this;
    private ActivityUserHomeBinding viewBinding;
    private MenuView.ItemView homes, packs;
    public static int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_home);
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
        fragment = new UserHomeFragment();
        loadFragment(fragment);
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

    public void loadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.home_: {

                fragment = new HomeFragment();
            }
            break;
            case R.id.plans_pack: {
                Toast.makeText(getApplicationContext(), "success full click2------home", Toast.LENGTH_SHORT).show();
            }
            break;
           /* case R.id.shop_extra: {
                fragment = new HomeFragment();
            }
            break;
            case R.id.bills_payment:
                Toast.makeText(getApplicationContext(), "success full click------home", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.my_profile:
                fragment = new EditProfile();
                break;
            case R.id.my_account:
                Intent intentW = new Intent(context, VendorWalletActivity.class);
                context.startActivity(intentW);
                break;
            case R.id.request_shift:
                fragment = new RequestToShift();
                break;
           /* case R.id.vender_refer:

                break;*/
            case R.id.vender_sales:
                fragment = new SalesReportFragment();
                break;
        }

        if (fragment != null) {
            loadFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
