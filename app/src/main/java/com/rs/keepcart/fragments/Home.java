package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rs.keepcart.HomePage;
import com.rs.keepcart.R;
import com.rs.keepcart.adapter.DashBoardPagerAdapter;
import com.rs.keepcart.adapter.HomeViewPagerAdapter;
import com.rs.keepcart.databinding.FragmentHomeBinding;

import java.util.Timer;
import java.util.TimerTask;


public class Home extends Fragment {

    private HomeViewPagerAdapter homeAdapter;
    private FragmentHomeBinding viewBinding;
    private DashBoardPagerAdapter dashAdapter;
    private Context context;
    int[] IMAGES;
    int currentPage = 0;
    int NUM_PAGES = 0;
    int dotscount;
    ImageView[] dots;
    Timer swipeTimer;
    Runnable Update;
    private int currentPostion;
    private TextView titleSet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_home, container, false);
      viewBinding = DataBindingUtil.bind(view);
        titleSet = ((HomePage)getActivity()).findViewById(R.id.page_title);
        titleSet.setText("KeepCart");
      context = getActivity();
        inItPager();
        homeInternalFrag();
        return view;
    }
    public void inItPager()
    {  int i;
        IMAGES = new int[]{R.drawable.banner, R.drawable.banner,R.drawable.banner,R.drawable.banner,R.drawable.banner};

        dashAdapter = new DashBoardPagerAdapter(context, IMAGES);
        viewBinding.viewpagerfragemnt.setAdapter(dashAdapter);
        dotscount = IMAGES.length;
        dots = new ImageView[dotscount];
        for(i =0; i<dotscount;i++)
        {
          dots[i] =new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            viewBinding.sliderdots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
        viewBinding.viewpagerfragemnt.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i;
                for (i = 0; i < dotscount; i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));

                }
                dots[position % dotscount].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        try {
            swipeTimer = new Timer();
            swipeTimer.scheduleAtFixedRate(new MyTimerTask(), 1000, 2000);
        }catch (Exception e)
        {
            Log.e(e.getMessage(),"exception timmer");
        }
    }
    public class MyTimerTask extends TimerTask
    {

        @Override
        public void run()
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    currentPostion++;
                    viewBinding.viewpagerfragemnt.setCurrentItem(currentPostion);


                }
            });

        }
    }

    public void homeInternalFrag()
    {
        viewBinding.tabLayoutFrag.setupWithViewPager(viewBinding.viewpagerTab);
        homeAdapter =new HomeViewPagerAdapter(context,getActivity().getSupportFragmentManager(),viewBinding.tabLayoutFrag.getTabCount());

        viewBinding.viewpagerTab.setAdapter(homeAdapter);
        viewBinding.tabLayoutFrag.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewBinding.viewpagerTab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
}
