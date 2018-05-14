package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.UserListRecyclerAdapter;
import com.rs.keepcart.databinding.FragmentNewUserBinding;

public class NewUser extends Fragment {

    private UserListRecyclerAdapter recyclerAdapter;
    private FragmentNewUserBinding viewBinding;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);
        viewBinding = DataBindingUtil.bind(view);
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.userlistRecyclerView.setAdapter(new UserListRecyclerAdapter(getActivity()));
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
