package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rs.keepcart.R;

/**
 * Created by sam on 4/23/2018.
 */

public class DashBoardPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private int [] images;


    public DashBoardPagerAdapter(Context context, int [] images)
    {
        this.context = context;
        this.images = images;
    }


    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.viewpager_images,container,false);
        ImageView imageView = (ImageView)v.findViewById(R.id.inViewPagerImage);
        position = position % images.length;
        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(v,0);
        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ViewPager vp = (ViewPager)container ;
        View view = (View)object;
        vp.removeView(view);

    }

}
