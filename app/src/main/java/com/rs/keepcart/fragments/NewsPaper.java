package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewsPaperBinding;

import java.util.ArrayList;
import java.util.List;


public class NewsPaper extends Fragment implements View.OnClickListener {
   private NewsAdapter newsAdapter;
   private FragmentNewsPaperBinding viewBinding;
   private EditProfile editProfile;
   private Fragment fragment;
   private FragmentTransaction fragmentTransaction;
   private Context context;
   List<String> itemName;
    int[] itemImag;
    private PopupMenu popupOption;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper, container, false);

        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        inItList();

        //viewBinding.recyclerNewsPaper.addItemDecoration();
        onClickSet();
        popupOptionMenu();
        return view;
    }
    public void onClickSet()
    {
        viewBinding.imageOption.setOnClickListener(this);
        viewBinding.profileImage.setOnClickListener(this);
    }
    public void inItList()
    {   itemName = new ArrayList<>();
        itemImag = new int[]{R.drawable.black_male,R.drawable.newspaper, R.drawable.offers__icon, R.drawable.magazine,
                R.drawable.sales_report, R.drawable.wallet_filled};

        itemName.add("User");
        itemName.add("NewsPaper List");
        itemName.add("Offers");
        itemName.add("Magazines");
        itemName.add("Sale Report");
        itemName.add("Wallet");
        viewBinding.recyclerNewsPaper.setLayoutManager(new GridLayoutManager(context,3));
        viewBinding.recyclerNewsPaper.setAdapter(new NewsAdapter(context,itemName, itemImag));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageOption:
                popupOption.show();
                break;
            case R.id.profileImage:
                if(fragment == null)
                {
                    fragment = new EditProfile();
                    loadRecycleItemFragment(fragment);
                }
                else {
                    loadRecycleItemFragment(fragment);
                }
                fragmentTransaction.addToBackStack(null);
                break;
        }
    }
    public void popupOptionMenu()
    {
        popupOption = new PopupMenu(getActivity(),viewBinding.imageOption);
        popupOption.getMenuInflater().inflate(R.menu.pop_up_spiner_job, popupOption.getMenu());

        popupOption.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return true;
            }
        });
    }

    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
          private  List<String> itemName;
          private  int[] itemImag;
          private Context context;
          public NewsAdapter(Context context, List<String> itemName, int[] itemImag) {
              this.context =context;
              this.itemImag = itemImag;
              this.itemName = itemName;
          }

          @Override
          public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
              View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_action_recycle,parent,false);

              return new ViewHolder(view);
          }

          @Override
          public void onBindViewHolder(ViewHolder holder, final int position) {
              //position = position % images.length;

            holder.itemsName.setText(itemName.get(position));
            holder.itemsImage.setImageResource(itemImag[position]);
              if(position==3){
                  holder.viewHorizontal.setVisibility(View.GONE);

              }
              if(position==4){

                  holder.viewHorizontal.setVisibility(View.GONE);
              }
              if(position==5){
                  holder.viewHorizontal.setVisibility(View.GONE);
              }
              holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if(position==0){
                          if(fragment == null) {
                              fragment = new UserList();
                              loadRecycleItemFragment(fragment);
                          }
                          else {
                              loadRecycleItemFragment(fragment);
                          }
                          fragmentTransaction.addToBackStack(null);
                      }

                      if(position==1){
                          if(fragment == null) {
                              fragment = new NewsPaperList();
                              loadRecycleItemFragment(fragment);
                          }
                          else {
                              loadRecycleItemFragment(fragment);
                          }
                          fragmentTransaction.addToBackStack(null);
                      }
                      if(position==3){
                          if(fragment == null) {
                              fragment = new Magazines();
                              loadRecycleItemFragment(fragment);
                          }
                          else {
                              loadRecycleItemFragment(fragment);
                          }
                          fragmentTransaction.addToBackStack(null);
                      }
                      if(position==4)
                      {
                          if(fragment == null) {
                              fragment = new SalesReport();
                              loadRecycleItemFragment(fragment);
                          }
                          else {
                              loadRecycleItemFragment(fragment);
                          }
                          fragmentTransaction.addToBackStack(null);
                      }
                      if(position==5)
                      {
                          if(fragment == null) {
                              fragment = new WalletVendor();
                              loadRecycleItemFragment(fragment);
                          }
                          else {
                              loadRecycleItemFragment(fragment);
                          }
                          fragmentTransaction.addToBackStack(null);
                      }
                  }
              });
          }

          @Override
          public int getItemCount() {
              return itemImag.length;
          }

        public class ViewHolder extends RecyclerView.ViewHolder {
             private ImageView itemsImage;
             private TextView itemsName, viewVertical,viewHorizontal;
             private ConstraintLayout mainLayout;
              public ViewHolder(View itemView) {
                  super(itemView);

                  itemsImage = itemView.findViewById(R.id.action_itemsImages);
                  itemsName = itemView.findViewById(R.id.action_itemsName);
                  mainLayout = itemView.findViewById(R.id.mainLayout);
                  viewVertical = itemView.findViewById(R.id.view_vertical);
                  viewHorizontal = itemView.findViewById(R.id.view_horizontal);
              }
          }
      }
    public void loadRecycleItemFragment(Fragment fragment)
    {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
