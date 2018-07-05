package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rs.keepcart.login.SignUp;
import com.rs.keepcart.login.SignIn;

/**
 * Created by sam on 4/19/2018.
 */

public class LoginViewPagerAdapter extends FragmentStatePagerAdapter {
    String a[] = {"SignIn", "SignUp"};
    private Context context;

    public LoginViewPagerAdapter(Context context, FragmentManager fm) {
                super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            return new SignIn();
        }  else {

            return new SignUp();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = a[0];

        } else {
            title = a[1];
        }

        return title;

    }

}
