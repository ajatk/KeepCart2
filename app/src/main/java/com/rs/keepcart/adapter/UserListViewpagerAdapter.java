package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rs.keepcart.fragments.NewUser;

/**
 * Created by sam on 4/30/2018.
 */

public class UserListViewpagerAdapter extends FragmentStatePagerAdapter {
    String a[] = {"All", "New","Stoped"};
    private Context context;

    public UserListViewpagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {

            return new NewUser();
        }  else if(position==1) {

            return new NewUser();
        } else {
            return new NewUser();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = a[0];

        } else if (position == 1)
        {
            title = a[1];
        } else{
            title = a[2];
        }

        return title;

    }

}
