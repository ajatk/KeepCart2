package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.UserListRecyclerAdapter;
import com.rs.keepcart.adapter.UserListViewpagerAdapter;
import com.rs.keepcart.databinding.FragmentMagazinesBinding;
import com.rs.keepcart.databinding.FragmentUserListBinding;


public class Magazines extends Fragment {

    private FragmentMagazinesBinding viewbinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_magazines, container, false);
        viewbinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewbinding.userTabLayout.setupWithViewPager(viewbinding.magazineViewPager);
        viewbinding.magazineViewPager.setAdapter(new MagazinesViewPagerAdapter(context, getActivity().getSupportFragmentManager()));
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class MagazinesViewPagerAdapter extends FragmentStatePagerAdapter{
        String a[] = {"Education", "New interior","Shef","Recipies"};
        private Context context;


        public MagazinesViewPagerAdapter(Context context, FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new MagazinesList();
            }  else if(position==1) {

                return new MagazinesList();
            } else if(position==2) {
                return new MagazinesList();
            }else {
                return new MagazinesList();
            }

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = a[0];

            } else if (position == 1)
            {
                title = a[1];
            } else{
                title = a[2];
            }

            return title;
        }
    }
}
