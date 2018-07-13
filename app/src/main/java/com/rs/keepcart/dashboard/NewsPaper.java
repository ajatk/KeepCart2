package com.rs.keepcart.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rs.keepcart.R;
import com.rs.keepcart.adapter.TotalBillsRecyclerAdapter;
import com.rs.keepcart.connectivity.NoInternetConection;
import com.rs.keepcart.dashboard.dashBoardModel.VendorDetail;
import com.rs.keepcart.dashboard.dashBoardModel.VendorPreviou;
import com.rs.keepcart.databinding.FragmentNewsPaperBinding;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.userPart_appUserScreens.userDashBoard.UserHomeActivity;
import com.rs.keepcart.utills.MySharedData;
import com.rs.keepcart.wallet.VendorWalletActivity;
import com.rs.keepcart.magazines.ActivityMagazines;
import com.rs.keepcart.magazines.Magazines;
import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.newsPaperList.NewsPaperList;
import com.rs.keepcart.newsPaperList.NewsPaperListActivity;
import com.rs.keepcart.salesReport.SalesReportFragment;
import com.rs.keepcart.salesReport.SalesReportActivity;
import com.rs.keepcart.vendorUserList.UserDetail;
import com.rs.keepcart.vendorUserList.UserList;

import com.rs.keepcart.vendorUserList.UserListActivity;

import com.rs.keepcart.connectivity.ConnectivityReceiver;
import com.rs.keepcart.utills.MyApplication;

import java.util.ArrayList;
import java.util.List;


public class NewsPaper extends Fragment implements View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {
   private NewsAdapter newsAdapter;
   private FragmentNewsPaperBinding viewBinding;
   private EditProfile editProfile;
   private Fragment fragment=null;
    String image;
   private UserList userList;
   private NewsPaperList newsPaperList;
   private Magazines magazines;
   private SalesReportFragment salesReport;
   private FragmentTransaction fragmentTransaction;
   private Context context;
    private SetNewspaperInfoModelClass infoClass;
    private List<UserDetail> userDetail;

    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    List<String> itemName;
    int[] itemImag;
    private PopupMenu popupOption;
    private LinearLayoutManager linearLayoutManager;
    private Bundle bundle;
    private TextView totalBills;
    public List<VendorDetail> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<VendorDetail> vendorList) {
        this.vendorList = vendorList;
        if(vendorList != null) {
            onClickSet();
            //inItList();
        }
    }

    private List<VendorDetail>vendorList;

    public List<VendorPreviou> getVendorPrevious() {
        return vendorPrevious;
    }

    public void setVendorPrevious(List<VendorPreviou> vendorPrevious) {
        this.vendorPrevious = vendorPrevious;
        if(vendorPrevious!=null)
        {
            totalBillsRecycle();
        }
    }

    private List<VendorPreviou> vendorPrevious;

    private  int i ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper, container, false);

        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        totalBills = view.findViewById(R.id.totalBillsTv);
        inItList();
         image = MySharedData.getGeneralSaveSession("image_saved");

        Glide.with(context).load(Base_URL + image )
                .error(R.drawable.banner)
                .into(viewBinding.profileImage);
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void onClickSet()
    {
        viewBinding.imageOption.setOnClickListener(this);
        viewBinding.profileImage.setOnClickListener(this);
        viewBinding.leftScroll.setOnClickListener(this);
        viewBinding.rightScroll.setOnClickListener(this);

        try{
            if(vendorList==null){

             }
             else {

                viewBinding.vendorNameId.setText(vendorList.get(i).getName() + " "+ vendorList.get(i).getVendorId());
                if(vendorList.get(i).getDueAmounts().getDueAmount()==null)
                {
                    viewBinding.dueBillAmountTv.setText("0");
                }else
                {
                    viewBinding.dueBillAmountTv.setText( vendorList.get(i).getDueAmounts().getDueAmount());
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void totalBillsRecycle()
    {
        if(vendorList==null)
        {

        }else{
            viewBinding.dueBillAmount.setVisibility(View.VISIBLE);
            totalBills.setVisibility(View.VISIBLE);
            viewBinding.textViewDueBill.setVisibility(View.VISIBLE);
            viewBinding.dueBillAmountTv.setVisibility(View.VISIBLE);

            viewBinding.recyclerBills.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

            viewBinding.recyclerBills.setAdapter(new TotalBillsRecyclerAdapter(getActivity(),vendorPrevious));

        }
    }
    public void inItList()
    {   itemName = new ArrayList<>();
        itemImag = new int[]{R.drawable.black_male,R.drawable.newspaper, R.drawable.offers__icon,
                R.drawable.magazine,R.drawable.sales_report, R.drawable.wallet_filled};

        itemName.add("User");
        itemName.add("NewsPaper List");
        itemName.add("Offers");
        itemName.add("Magazines");
        itemName.add("Sale Report");
        itemName.add("Wallet");
        viewBinding.recyclerNewsPaper.setLayoutManager(new GridLayoutManager(context,3));
        viewBinding.recyclerNewsPaper.setAdapter(new NewsAdapter(context,itemName, itemImag, getChildFragmentManager()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageOption:
               // popupOption.show();
                Intent intentUser = new Intent(context, UserHomeActivity.class);
                context.startActivity(intentUser);
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
            case R.id.left_scroll:
                //Toast.makeText(context, "clicked left", Toast.LENGTH_SHORT).show();
                 viewBinding.recyclerBills.getLayoutManager().scrollToPosition(0);
                break;
            case R.id.right_scroll:
                viewBinding.recyclerBills.getLayoutManager().scrollToPosition(5);
                break;
        }
    }
   /* public void popupOptionMenu()
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
*/
    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
          private  List<String> itemName;
          private  int[] itemImag;
          private Context context;
          private FragmentManager fm;
          public NewsAdapter(Context context, List<String> itemName, int[] itemImag) {
              this.context =context;
              this.itemImag = itemImag;
              this.itemName = itemName;
          }

        public NewsAdapter(Context context, List<String> itemName, int[] itemImag, FragmentManager childFragmentManager) {
            this.context =context;
            this.itemImag = itemImag;
            this.itemName = itemName;
            fm =childFragmentManager;
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
                      fragmentTransaction = fm.beginTransaction();
                      switch (position) {
                          case 0:
                              Intent intent = new Intent(context, UserListActivity.class);
                              context.startActivity(intent);

                              break;
                          case 1:
                              Intent intentN = new Intent(context, NewsPaperListActivity.class);
                              context.startActivity(intentN);
                              break;
                          case 2:
//                              Intent intentC = new Intent(context, ComingSoonFragment.class);
//                              context.startActivity(intentC);
                              break;
                          case 3:
                              Intent intentM = new Intent(context, ActivityMagazines.class);
                              context.startActivity(intentM);
                              break;
                          case 4:
                              Intent intentS = new Intent(context, SalesReportActivity.class);
                              context.startActivity(intentS);
                              break;
                          case 5:
                              Intent intentW = new Intent(context, VendorWalletActivity.class);
                              context.startActivity(intentW);
                              break;
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
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
    @Override
    public void onResume() {
        super.onResume();
//        Glide.with(context).load(Base_URL + image )
//                .error(R.drawable.banner)
//                .into(viewBinding.profileImage);
        MyApplication.getInstance().setConnectivityListener((ConnectivityReceiver.ConnectivityReceiverListener) this);

    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
//            message = "Good! Connected to Internet";
//            color = Color.WHITE;
        } else {
//            message = "Sorry! Not connected to internet";
//            color = Color.RED;
            Intent intent = new Intent(context,NoInternetConection.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Snackbar snackbar = Snackbar
                    .make(viewBinding.getRoot(), "Sorry! Not connected to internet", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
        }
    }

}
