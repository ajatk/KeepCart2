package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rs.keepcart.HomePage;
import com.rs.keepcart.R;
import com.rs.keepcart.adapter.ExpandableWalletList;
import com.rs.keepcart.databinding.FragmentWalletVendorBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WalletVendor extends Fragment /*implements ExpandableListView.OnGroupClickListener */{
     private FragmentWalletVendorBinding viewBinding;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Context context;
    private TextView titleSet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_wallet_vendor, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context =getActivity();
        titleSet = ((HomePage)context).findViewById(R.id.page_title);
        titleSet.setText("Wallet");
        prepareListData();

        return view;
    }
    private void prepareListData() {

        listDataHeader = new ArrayList();
        listDataChild = new HashMap<>();
        try {
            listDataHeader.add("Transanction History1");
            listDataHeader.add("Transanction History2");
            listDataHeader.add("Transanction History3");
            listDataHeader.add("Transanction History4");
            listDataHeader.add("Transanction History5");
            listDataHeader.add("Transanction History6");

        }catch (Exception e)
        {

        }

        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Shawshank Redemption");

        for (int j =0; j<listDataHeader.size();j++)
            {
               listDataHeader.get(j);
               for(int i =0; i<=listDataChild.size();i++)
            {
                listDataChild.put(listDataHeader.get(j),top250);
            }
            }

        viewBinding.walletExpandableListView.setAdapter(new ExpandableWalletList(context, listDataHeader,listDataChild));
        viewBinding.walletExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(),
                        "Group Clicked " + listDataHeader.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        viewBinding.walletExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        viewBinding.walletExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        viewBinding.walletExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
