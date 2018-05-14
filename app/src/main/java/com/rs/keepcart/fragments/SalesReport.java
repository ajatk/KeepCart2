package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.UserListRecyclerAdapter;
import com.rs.keepcart.adapter.UserListViewpagerAdapter;
import com.rs.keepcart.databinding.FragmentSalesReportBinding;
import com.rs.keepcart.databinding.FragmentUserListBinding;


public class SalesReport extends Fragment {

    private FragmentSalesReportBinding viewbinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales_report, container, false);
        viewbinding = DataBindingUtil.bind(view);
        context = getActivity();

        viewbinding.salesReportTabLayout.setupWithViewPager(viewbinding.salesReportViewPager);
        viewbinding.salesReportViewPager.setAdapter(new SalesReportViewpagerAdapter(context, getActivity().getSupportFragmentManager()));
        return view;
    }


    public class SalesReportViewpagerAdapter extends FragmentStatePagerAdapter {
        String a[] = {"NewsPaper", "Magazine"};
        private Context context;

        public SalesReportViewpagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new NewsPaperListSales();
            }   else {
                return new MagazinesListSales();
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else{
                title = a[1];
            }
            return title;

        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
     /* public void tabSelectedMethod()
    {
        viewbinding.salesReportViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             if(position==0)
             {
                 viewbinding.salesReportTabLayout.setBackgroundColor(R.color.gray_set);
             }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }*/
}
