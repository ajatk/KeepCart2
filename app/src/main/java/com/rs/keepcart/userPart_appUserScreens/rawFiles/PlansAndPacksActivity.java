package com.rs.keepcart.userPart_appUserScreens.rawFiles;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityPlansAndPacksBinding;
import com.rs.keepcart.salesReport.MagazinesListSales;
import com.rs.keepcart.salesReport.NewsPaperListSales;
import com.rs.keepcart.salesReport.SalesReportActivity;

public class PlansAndPacksActivity extends AppCompatActivity {

    private ActivityPlansAndPacksBinding viewbinding;
    private PlansAndpacksViewpagerAdapter viewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_and_packs);

        viewbinding.salesReportTabLayout.setupWithViewPager(viewbinding.salesReportViewPager);
        viewpagerAdapter = new PlansAndpacksViewpagerAdapter(this, getSupportFragmentManager());
        viewbinding.salesReportViewPager.setAdapter(viewpagerAdapter);

    }

    public class PlansAndpacksViewpagerAdapter extends FragmentStatePagerAdapter {
        String a[] = {"NewsPaper", "Magazine"};
        private Context context;

        public PlansAndpacksViewpagerAdapter(Context context, FragmentManager fm) {
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
    public void backButton(View view)
    {
        if (getFragmentManager().getBackStackEntryCount()>0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
