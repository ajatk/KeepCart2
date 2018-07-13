package com.rs.keepcart.vendorUserList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewUserBinding;
import com.rs.keepcart.model.SetNewspaperInfoModelClass;

import java.util.ArrayList;
import java.util.List;

public class NewUser extends Fragment {
    private Context context;
    private UserListRecyclerAdapter recyclerAdapter;
    private FragmentNewUserBinding viewBinding;
    private RecyclerView rv;
    private SetNewspaperInfoModelClass infoClass;
    private ArrayList<Boolean>arrayList;
    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
         if(userDetail!=null)
         {
             adapterMethod();
         }
    }

    private List<UserDetail> userDetail;

    private FragmentManager fragmentManager;
    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);
        viewBinding = DataBindingUtil.bind(view);
        userDetail = new ArrayList<>();
        context = getActivity();
        arrayList = new ArrayList<>();
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    public void adapterMethod()
    {
        recyclerAdapter  = new UserListRecyclerAdapter(context,userDetail,getActivity().getSupportFragmentManager());
        for(int i= 0; i<recyclerAdapter.getItemCount();i++){
            arrayList.add(false);
        }

        viewBinding.userlistRecyclerView.setAdapter(recyclerAdapter);
        setSearchTextListener();
    }


    public void setSearchTextListener(){
        viewBinding.searchViewUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                              @Override
                                                              public boolean onQueryTextSubmit(String query) {
                                                                  recyclerAdapter.getFilter().filter(query);
                                                                  return false;
                                                              }

                                                              @Override
                                                              public boolean onQueryTextChange(String newText) {

                                                                  recyclerAdapter.getFilter().filter(newText);

                                                                  return true;
                                                              }
                                                          }
        );

    }
}
