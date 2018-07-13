package com.rs.keepcart.dashboard;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.paytm.pgsdk.Log;
import com.rs.keepcart.R;
import com.rs.keepcart.connectivity.NoInternetConection;
import com.rs.keepcart.databinding.ActivityHomePageBinding;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.editProfile.profileModel.GetDataProfileModelClass;
import com.rs.keepcart.fragments.Aboutusfragment;
import com.rs.keepcart.fragments.RequestToShift;
import com.rs.keepcart.login.LoginActivity;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.salesReport.SalesReportActivity;
import com.rs.keepcart.session.SessionManager;
import com.rs.keepcart.connectivity.ConnectivityReceiver;
import com.rs.keepcart.utills.MyApplication;
import com.rs.keepcart.utills.MySharedData;
import com.rs.keepcart.wallet.VendorWalletActivity;

import retrofit2.Call;
import retrofit2.Callback;

import static com.rs.keepcart.editProfile.EditProfile.Base_URL;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener {
    public static final String TAG = HomeActivity.class.getSimpleName();
    public static int navItemIndex = 0;
    NavigationView navigationView;
    SessionManager session;
    String image_saved, Vendor_name,email_dash, imgg;
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
       // image_saved = MySharedData.getGeneralSaveSession("imgg");
        image_saved = MySharedData.getGeneralSaveSession("image_saved");
        Vendor_name = MySharedData.getGeneralSaveSession("Vendor_name");
         email_dash = MySharedData.getGeneralSaveSession("email_dash");
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

        Glide.with(context).load(Base_URL + image_saved )
                .error(R.drawable.banner)
                .into(headerImg);

        name.setText(Vendor_name);
        email.setText(email_dash);

        toolbar.setNavigationIcon(R.drawable.tool_bar);
        if (savedInstanceState != null) {

        } else {
            // You probably want to add your ListFragment here.
        }
        fragment = new HomeFragment();
        loadFragment(fragment);
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
           // fragmentTransaction.addToBackStack(fragment.getClass().getName());
            //fragmentTransaction.commit();
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
        Glide.with(context).load(Base_URL + image_saved )
                .error(R.drawable.banner)
                .into(headerImg);
        setProfilData();
    }
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);

        }/* else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();*/
        else {
            Intent mainActivity = new Intent(Intent.ACTION_MAIN);
            mainActivity.addCategory(Intent.CATEGORY_HOME);
            mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainActivity);
        }
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

    public void setProfilData() {
        String id_ = MySharedData.getGeneralSaveSession("userId");
        ApiInterface retrofitInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetDataProfileModelClass> call = retrofitInterface.getProfileData(id_);
        // viewBinding.progressBarLog.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<GetDataProfileModelClass>() {
            @Override
            public void onResponse(Call<GetDataProfileModelClass> call, retrofit2.Response<GetDataProfileModelClass> response) {

                // mProgressBar.setVisibility(View.GONE);
                GetDataProfileModelClass responseBody = response.body();

                if (response.isSuccessful()) {

                    if (response.body().getStatus() != null) {
                        if (response.body().getStatus() == 200) {
                            //viewBinding.editName.ed

                            MySharedData.setGeneralSaveSession("imgg",responseBody.getProfile().getImage().toString());

                            // Toast.makeText(context, "response 200", Toast.LENGTH_SHORT).show();
                            //Snackbar.make(titleSet, responseBody.getProfile().getm,Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "response Status is not 200", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetDataProfileModelClass> call, Throwable t) {


                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}


