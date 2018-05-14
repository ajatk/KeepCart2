package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rs.keepcart.fragments.NewsPaper;
import com.rs.keepcart.login.SignIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 4/23/2018.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    String a[] = {"NewsPaper", "Shopping ","Services" };
    public Context context;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;

    }

    public HomeViewPagerAdapter(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {


        if (position == 0) {

            return new NewsPaper();
        }  else if (position ==1){

            return new SignIn();
        }
        else{
            return new NewsPaper();
        }
    }
     public void addFragment(Fragment fragment, String title)
     {
         mFragmentList.add(fragment);
         mFragmentTitleList.add(title);
      }
    @Override
    public int getCount() { return 3; }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = a[0];

        } else if(position==1) {
            title = a[1];
        }
        else
        {
            title = a [2];
        }

        return title;

    }

}
