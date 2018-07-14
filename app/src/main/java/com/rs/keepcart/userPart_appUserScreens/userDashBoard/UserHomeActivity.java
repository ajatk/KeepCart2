package com.rs.keepcart.userPart_appUserScreens.userDashBoard;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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
        {
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private HomeFragment homeFragment;
    NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
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


        bottomNavigationView =  findViewById(R.id.bottomNav_view);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher_);
        fragment = new UserHomeFragment();
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }

    public void loadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
