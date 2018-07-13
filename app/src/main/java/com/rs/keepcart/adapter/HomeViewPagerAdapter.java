package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rs.keepcart.dashboard.dashBoardModel.VendorDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 4/23/2018.
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    private  ArrayList<Fragment> mFragmentList = new ArrayList<>();
    String a[] = {"NewsPaper", "Shopping ","Services" };
    public Context context;

    private List<VendorDetail> vendorList;

    public HomeViewPagerAdapter(ViewPager viewPager, Context context, FragmentManager fm, List<VendorDetail> vendor_List, int tabCount, ArrayList<Fragment> mFragmentList) {
        super(fm);
        this.context = context;
        this.vendorList = vendor_List;
        this.mFragmentList = mFragmentList;
            viewPager.setOffscreenPageLimit(3);
    }


    @Override
    public Fragment getItem(int position) {
            return mFragmentList.get(position);


    }
     public void addFragment(Fragment fragment)
     {
         mFragmentList.add(fragment);

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
