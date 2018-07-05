package com.rs.keepcart.userlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.UserListViewpagerAdapter;
import com.rs.keepcart.databinding.FragmentUserListBinding;


public class UserList extends Fragment {

     private UserListRecyclerAdapter recyclerAdapter;
     private UserListViewpagerAdapter viewpagerAdapter;
     private FragmentUserListBinding viewbinding;
     private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        viewbinding = DataBindingUtil.bind(view);
        context = getActivity();

        viewbinding.userTabLayout.setupWithViewPager(viewbinding.userViewPager);
       // viewbinding.userViewPager.setAdapter(new UserListViewpagerAdapter(context,getChildFragmentManager()));

        return view;
    }


}
