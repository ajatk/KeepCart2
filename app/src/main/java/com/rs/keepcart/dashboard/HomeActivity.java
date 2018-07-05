package com.rs.keepcart.dashboard;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.connectivity.NoInternetConection;
import com.rs.keepcart.databinding.ActivityHomePageBinding;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.editProfile.EditprofileViewModelClass;
import com.rs.keepcart.editProfile.profileModel.EditProfileModelClass;
import com.rs.keepcart.editProfile.profileModel.GetDataProfileModelClass;
import com.rs.keepcart.fragments.Aboutusfragment;
import com.rs.keepcart.fragments.RequestToShift;
import com.rs.keepcart.login.LoginActivity;
import com.rs.keepcart.login.SignIn;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.salesReport.SalesReportActivity;
import com.rs.keepcart.session.SessionManager;
import com.rs.keepcart.connectivity.ConnectivityReceiver;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MyApplication;
import com.rs.keepcart.utills.MySharedData;
import com.rs.keepcart.wallet.VendorWalletActivity;

import static com.rs.keepcart.editProfile.EditProfile.Base_URL;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener {
    public static int navItemIndex = 0;
    NavigationView navigationView;
    SessionManager session;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private HomeFragment homeFragment;
    private Context context = this;
    private ActivityHomePageBinding viewBinding;
    private MenuView.ItemView homes, packs;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawer;
    private ActionBar mActionBar;
    private Boolean pass = false;

    private boolean mToolBarNavigationListenerIsRegistered = false;
    private ImageView headerImg;
    private TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        session = new SessionManager(getApplicationContext());

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        headerImg = view.findViewById(R.id.imageView);
        name = view.findViewById(R.id.id_name);
        email = view.findViewById(R.id.id_email);

        String image_saved = MySharedData.getGeneralSaveSession("image_saved");
        String Vendor_name = MySharedData.getGeneralSaveSession("Vendor_name");
        String email_dash = MySharedData.getGeneralSaveSession("email_dash");
        Glide.with(context).load(Base_URL + image_saved )
                .error(R.drawable.banner)
                .into(headerImg);

        name.setText(Vendor_name);
        email.setText(email_dash);

        String image = MySharedData.getGeneralSaveSession("image_saved");

        toolbar.setNavigationIcon(R.drawable.tool_bar);
        if (savedInstanceState != null) {

        } else {
            // You probably want to add your ListFragment here.
        }
        fragment = new HomeFragment();
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);

        } else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }
    }


    public void loadFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
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
            /* case R.id.plans_pack: {
                Toast.makeText(getApplicationContext(), "success full click2------home", Toast.LENGTH_SHORT).show();
            }
            break;
           case R.id.shop_extra: {
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
                //fragment = new SalesReportFragment();

                Intent intentS = new Intent(HomeActivity.this, SalesReportActivity.class);
                startActivity(intentS);

                break;
            case R.id.aboutusTxt:
                fragment = new Aboutusfragment();
                break;
            case R.id.vender_logout:
                Logout();
                break;
        }
       /* public void onResume() { wasShaken = false; }*/
        if (fragment != null) {
            loadFragment(fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Logout() {
        session.setLogin(false);
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void backButton(View view) {
        finish();
    }
    @Override
    public void onResume() {
        super.onResume();
        pass = false;
        MyApplication.getInstance().setConnectivityListener((ConnectivityReceiver.ConnectivityReceiverListener) this);
    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
        } else {
//            message = "Sorry! Not connected to internet";
//            color = Color.RED;
            Intent intent = new Intent(context,NoInternetConection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Snackbar snackbar = Snackbar
                    .make(viewBinding.getRoot(), "Sorry! Not connected to internet", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
        }
    }


}


