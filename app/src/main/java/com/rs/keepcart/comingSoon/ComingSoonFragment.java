package com.rs.keepcart.comingSoon;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentComingSoonBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class ComingSoonFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {
    int[] IMAGES;
    private HashMap<String, Integer> hashMap;
    private Context context;
    private SliderLayout sliderLayout;
    private ProgressDialog pDialog;
    private ArrayList<Integer> arrayList;
    private FragmentComingSoonBinding viewBinding;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        hashMap = new HashMap<String, Integer>();
        sliderLayout = view.findViewById(R.id.slider);
        SliderRequest();

        return view;
    }

    private void SliderRequest() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        showpDialog();
        try {
            inItPager();
            //sliderLayout =  view.findViewById(R.id.slider);
            //  sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            //sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            //sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(3000);
            sliderLayout.addOnPageChangeListener(ComingSoonFragment.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inItPager() {
       // int i=0;
        arrayList = new ArrayList<Integer>();
       IMAGES = new int[]{R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner, R.drawable.banner};

       arrayList.add(R.drawable.aa);
       arrayList.add(R.drawable.ab);
       arrayList.add(R.drawable.ac);
       arrayList.add(R.drawable.ad);


//        String imag_e = banner_List.get(i).getImages();
//        String ids= banner_List.get(i).getId();
//        imagurl = Base_URL + imag_e;
//        inItPager();
//        for(Integer banner : IMAGES)
//        {
//            hashMap.put(String.valueOf(banner),banner);
//        }

        for(Integer banner : arrayList)
        {
            hashMap.put(String.valueOf(banner),banner);
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
}
