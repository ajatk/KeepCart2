package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.dashboard.dashBoardModel.Banner;

import java.util.List;

/**
 * Created by sam on 4/23/2018.
 */

public class DashBoardPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<Banner> bannerList;
    private int[] images;
    String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";

    public DashBoardPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    public DashBoardPagerAdapter(Context context, List<Banner> banner_list) {
        this.context = context;
        this.bannerList = banner_list;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.viewpager_images, container, false);
        ImageView imageView = v.findViewById(R.id.inViewPagerImage);
        try {
            position = position % bannerList.size();
        Glide.with(context).load(Base_URL + bannerList.get(position).getImages())
                .error(R.drawable.banner)
                .into(imageView);
            ViewPager viewPager = (ViewPager) container;
            viewPager.addView(v, 0);
        } catch (ArithmeticException a) {
            a.printStackTrace();
        }catch (Exception e)
        {

        }

        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

}
