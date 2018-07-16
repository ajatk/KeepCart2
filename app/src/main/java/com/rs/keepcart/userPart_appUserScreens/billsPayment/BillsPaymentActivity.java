package com.rs.keepcart.userPart_appUserScreens.billsPayment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityBillsPaymentBinding;
import com.rs.keepcart.databinding.ActivityMagaZineTabBinding;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.userPart_appUserScreens.shopAndExtra.MagaZineTabActivity;
import com.rs.keepcart.userPart_appUserScreens.shopAndExtra.UserMagazineListFragment;

import java.util.ArrayList;
import java.util.List;

public class BillsPaymentActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private ActivityBillsPaymentBinding viewbinding;
    private BillsPaymentAdapter viewPagerTabAdapter;
    private Toolbar toolbar;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        viewbinding = DataBindingUtil.setContentView(this,R.layout.activity_bills_payment);
        viewbinding.userTabLayout.setupWithViewPager(viewbinding.billsViewPager);
        setUpViewPager(viewbinding.billsViewPager);
        title = findViewById(R.id.page_title);
        title.setText(R.string.Bills_And_Payments);


    }
    public void setUpViewPager(ViewPager viewPager)
    {
        fragmentList.add(new BillsFragment());
        fragmentList.add(new PaymentFragment());

        viewPagerTabAdapter = new BillsPaymentAdapter(this,getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(viewPagerTabAdapter);

    }
    public void backButton(View view) {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
    public class BillsPaymentAdapter extends FragmentStatePagerAdapter {
        String a[] = {"Past Bills", "Past Payments"};
        Resource resource;
        List<Fragment> mfragmentList;
        Context context;

        public BillsPaymentAdapter(Context context, FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
            super(supportFragmentManager);
            this.context = context;
            this.mfragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {

//         int c_id = Integer.parseInt(magzinesDetails.get(position).getId());
            return mfragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mfragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else if (position == 1) {
                title = a[1];
            }
            return title;
        }


    }
}
