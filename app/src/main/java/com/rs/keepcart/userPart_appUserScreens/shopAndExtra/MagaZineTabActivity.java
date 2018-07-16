package com.rs.keepcart.userPart_appUserScreens.shopAndExtra;

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

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityMagaZineTabBinding;
import com.rs.keepcart.magazines.ActivityMagazines;
import com.rs.keepcart.magazines.MagazinesList;
import com.rs.keepcart.retrofit.Resource;

import java.util.ArrayList;
import java.util.List;

public class MagaZineTabActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private ActivityMagaZineTabBinding viewbinding;
    private MagazinesViewPagerAdapter viewPagerTabAdapter;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        viewbinding = DataBindingUtil.setContentView(this,R.layout.activity_maga_zine_tab);
        viewbinding.userTabLayout.setupWithViewPager(viewbinding.magazineViewPager);
        setUpViewPager(viewbinding.magazineViewPager);
        title = findViewById(R.id.page_title);
        title.setText(R.string.shop_and_extra);
    }
    public void setUpViewPager(ViewPager viewPager)
    {
        fragmentList.add(new UserMagazineListFragment());
        fragmentList.add(new UserMagazineListFragment());
        fragmentList.add(new UserMagazineListFragment());
        fragmentList.add(new UserMagazineListFragment());


        viewPagerTabAdapter = new MagazinesViewPagerAdapter(this,getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(viewPagerTabAdapter);

    }
    public void backButton(View view) {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
    public class MagazinesViewPagerAdapter extends FragmentStatePagerAdapter {
        String a[] = {"Education", "New interior", "Sports", "Recipies"};
        Resource resource;
        List<Fragment> mfragmentList;
        Context context;

        public MagazinesViewPagerAdapter(Context context, FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
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
            } else if (position == 2) {
                title = a[2];
            } else {
                title = a[3];
            }

            return title;
        }


    }
}
