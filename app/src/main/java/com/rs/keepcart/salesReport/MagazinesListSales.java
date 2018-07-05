package com.rs.keepcart.salesReport;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentMagazinesListBinding;
import com.rs.keepcart.databinding.FragmentMagazinesListSalesBinding;
import com.rs.keepcart.fragments.MagazineMonthHistory;


public class MagazinesListSales extends Fragment {
    private FragmentMagazinesListSalesBinding viewBinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_magazines_list_sales, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBinding.salesReportTabLayout1.setupWithViewPager(viewBinding.salesReportViewPager1);
        viewBinding.salesReportViewPager1.setAdapter(new SalesReportAdapter1(context, getChildFragmentManager()));
        return view;
    }
    public class SalesReportAdapter1 extends FragmentStatePagerAdapter {
        String a[] = {"This Month", "lastMonth","pastMonth" };
        private Context context;

        public SalesReportAdapter1(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new MagazineMonthHistory();
            }  else {
                return new MagazineMonthHistory();
            }

        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else
            {
                title = a[1];
            }

            return title;

        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
