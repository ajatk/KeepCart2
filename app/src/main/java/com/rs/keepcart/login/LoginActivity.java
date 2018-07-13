package com.rs.keepcart.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.LoginViewPagerAdapter;
import com.rs.keepcart.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    LoginViewPagerAdapter pAdapter;
    private ActivityLoginBinding viewBinding;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
         viewBinding.slidingTabs.setupWithViewPager(viewBinding.viewPagerTest);
         viewBinding.viewPagerTest.setAdapter(new LoginViewPagerAdapter(this,getSupportFragmentManager()));
         viewBinding.viewPagerTest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             }

             @Override
             public void onPageSelected(int position) {
                 viewBinding.selectedTab1.setVisibility(View.GONE);
                 viewBinding.selectedTab2.setVisibility(View.GONE);
                 if(position==0)
                 {
                     viewBinding.selectedTab2.setVisibility(View.VISIBLE);

                 }
                 if(position==1)
                 {

                     viewBinding.selectedTab1.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });

    }

    public void signup_link(View view) {
        viewBinding.viewPagerTest.setCurrentItem(1);

    }

    public void signIn_link(View view) {
        viewBinding.viewPagerTest.setCurrentItem(0);

    }
    public void onBackPressed() {
        finish();
    }

    }
