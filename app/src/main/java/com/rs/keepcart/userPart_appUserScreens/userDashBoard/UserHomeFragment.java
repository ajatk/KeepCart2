package com.rs.keepcart.userPart_appUserScreens.userDashBoard;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.rs.keepcart.R;
import com.rs.keepcart.adapter.DashBoardPagerAdapter;
import com.rs.keepcart.adapter.HomeViewPagerAdapter;
import com.rs.keepcart.comingSoon.ComingSoonFragment;
import com.rs.keepcart.comingSoon.ComingSoonServiceFragment;
import com.rs.keepcart.dashboard.NewsPaper;
import com.rs.keepcart.dashboard.dashBoardModel.Banner;
import com.rs.keepcart.databinding.FragmentUserHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class UserHomeFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {
    private FragmentUserHomeBinding viewBinding;
    private UserHomeViewPagerAdapter homeAdapter;
    //private DashBoardPagerAdapterr dashAdapter;

    int[] IMAGES;
    int currentPage = 0;
    int NUM_PAGES = 0;
    int dotscount;
    ImageView[] dots;
    Timer swipeTimer;
    Runnable Update;
    private int currentPostion;
    private TextView titleSet;
    private HashMap<String, String> hashMaps;
    private HashMap<String, Integer> hashMap;
    private Context context;
    private SliderLayout sliderLayout;
    private ProgressDialog pDialog;
    private ArrayList<Integer> arrayList;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_home, container, false);
        viewBinding = DataBindingUtil.bind(view);
        // homeInternalFrag();
        titleSet = ((UserHomeActivity) getActivity()).findViewById(R.id.page_title);
        titleSet.setText("KeepCart");
        context = getActivity();
        hashMaps = new HashMap<String, String>();
        hashMap = new HashMap<String, Integer>();
        sliderLayout = view.findViewById(R.id.slider);
        SliderRequest();
        homeInternalFrag();
        //inItPager();
        return view;
    }


    private void SliderRequest() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        showpDialog();
        try {
            inItPagerr();
            //sliderLayout =  view.findViewById(R.id.slider);
            //  sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            //sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            //sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(3000);
            sliderLayout.addOnPageChangeListener(UserHomeFragment.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inItPagerr() {
        // int i=0;
        arrayList = new ArrayList<Integer>();
        IMAGES = new int[]{R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner};

        arrayList.add(R.drawable.aa);
        arrayList.add(R.drawable.ab);
        arrayList.add(R.drawable.ac);
        arrayList.add(R.drawable.ad);
        arrayList.add(R.drawable.ae);

//        String imag_e = banner_List.get(i).getImages();
//        String ids= banner_List.get(i).getId();
//        imagurl = Base_URL + imag_e;
//        inItPager();
//        for(Integer banner : IMAGES)
//        {
//            hashMap.put(String.valueOf(banner),banner);
//        }

        for (Integer banner : arrayList) {
            hashMap.put(String.valueOf(banner), banner);
        }
        try {

            for (String name : hashMap.keySet()) {
                TextSliderView textSliderView = new TextSliderView(context);
                textSliderView
                        //  .description(name)
                        .image(hashMap.get(name));
                //   .setScaleType(BaseSliderView.ScaleType.Fit)
                //  .setOnSliderClickListener(HomeFragment.this);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", name);
                sliderLayout.addSlider(textSliderView);
            }
            hidepDialog();
        } catch (Exception e) {

        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void homeInternalFrag() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new UserNewsPaperFragment());
        arrayList.add(new ComingSoonFragment());
        arrayList.add(new ComingSoonServiceFragment());

        homeAdapter = new UserHomeViewPagerAdapter( context, getChildFragmentManager(), arrayList);
        viewBinding.tabLayoutFrag.setupWithViewPager(viewBinding.viewpagerTab);
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

}

