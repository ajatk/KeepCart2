package com.rs.keepcart.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rs.keepcart.R;
import com.rs.keepcart.dashboard.dashBoardModel.VendorPreviou;
import com.rs.keepcart.databinding.TotalBillsOnDashboardBinding;

import java.util.List;

/**
 * Created by sam on 5/21/2018.
 */

public class TotalBillsRecyclerAdapter extends RecyclerView.Adapter<TotalBillsRecyclerAdapter.MyViewHolder> {
    private List<String> itemName;
    private  int[] itemImag;
    private Context context;

    private List<VendorPreviou> vendorPrevious;

    public TotalBillsRecyclerAdapter(Context context) {
        this.context = context;
    }


    public TotalBillsRecyclerAdapter(FragmentActivity activity, List<VendorPreviou> vendorPrevious) {
        this.context = context;
        this.vendorPrevious = vendorPrevious;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_bills_on_dashboard,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


             if(vendorPrevious!=null) {
                 try {

                     holder.cardView.setVisibility(View.VISIBLE);
                     if(vendorPrevious.get(position).getPreviousAmount()==null)
                     {
                         holder.price.setText( "0");
                     }else{
                         holder.price.setText( vendorPrevious.get(position).getPreviousAmount());
                     }

                     holder.totalMonths.setText( vendorPrevious.get(position).getCreatedDatetime());


                 } catch (NullPointerException e) {
                     e.printStackTrace();
                 }
             }

    }/*  if (list == null)
            return 0;
        else
            return list.size();*/

    @Override
    public int getItemCount() {
        if(vendorPrevious==null)
        {
          return 0 ;
        }else {

            return vendorPrevious.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
          private TotalBillsOnDashboardBinding viewBind;
          private TextView price, totalMonths, date;
          private CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.totalBillPrice);
            totalMonths =  itemView.findViewById(R.id.totalBillMonths);
            cardView = itemView.findViewById(R.id.mainCardView);
        }



    }
}
