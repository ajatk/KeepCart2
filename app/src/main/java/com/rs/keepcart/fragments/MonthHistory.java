package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentMonthHistoryBinding;
import com.rs.keepcart.databinding.NewpaperSalesReportListBinding;
import com.rs.keepcart.salesReport.newspaperSales.PendingAmount;
import com.rs.keepcart.salesReport.newspaperSales.Sale;

import java.util.List;


public class MonthHistory extends Fragment {

    private FragmentMonthHistoryBinding viewBinding;
    private Context context;
    private List<Sale> saleMonthList ;
    private List<PendingAmount> pendingAmountMonthList ;

    public List<Sale> getSaleMonthList() {
        return saleMonthList;
    }

    public List<PendingAmount> getPendingAmountMonthList() {
        return pendingAmountMonthList;
    }

    public void setSaleMonthList(List<Sale> saleMonthList) {
        this.saleMonthList = saleMonthList;
    }

    public void setPendingAmountMonthList(List<PendingAmount> pendingAmountMonthList) {
        this.pendingAmountMonthList = pendingAmountMonthList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_month_history, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBinding.monthListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewBinding.monthListRecyclerView.setAdapter(new MonthHistoryAdapter(getActivity(), saleMonthList));
        return view;
    }
    public class MonthHistoryAdapter extends RecyclerView.Adapter<MonthHistoryAdapter.ViewHolder>{
        private Context contextt;
        private List<Sale> saleMonthList;
        public MonthHistoryAdapter(FragmentActivity activity, List<Sale> saleMonthList) {
            this.contextt = activity;
            this.saleMonthList = saleMonthList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newpaper_sales_report_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            try
            {
                if(saleMonthList!=null){
                    holder.viewBinding.totalSale.setText(saleMonthList.get(position).getTotalSale());
                    holder.viewBinding.amounteRecieve.setText(saleMonthList.get(position).getReceivedAmount());
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            if(saleMonthList!=null)
            {
                return saleMonthList.size();
            }else {
                return 0;
            }

        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private NewpaperSalesReportListBinding viewBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
