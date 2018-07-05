package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rs.keepcart.userlist.NewUser;

import java.util.ArrayList;

/**
 * Created by sam on 4/30/2018.
 */

public class UserListViewpagerAdapter extends FragmentStatePagerAdapter {
    String a[] = {"All", "New","Stoped"};
    private Context context;
    private ArrayList<Fragment> mfragmentsList = new ArrayList<>();

    public UserListViewpagerAdapter(ViewPager viewPager, Context context, FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        viewPager.setOffscreenPageLimit(3);
        mfragmentsList = fragmentList;

    }


    @Override
    public Fragment getItem(int position) {

        return mfragmentsList.get(position);
       /* if (position == 0) {

            return new NewUser();
        }  else if(position==1) {

            return new NewUser();
        } else {
            return new NewUser();
        }*/

    }

    @Override
    public int getCount() {
        if(mfragmentsList!=null)
        {
            return mfragmentsList.size();
        }else {
            return 0;
        }

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
