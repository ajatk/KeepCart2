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
import android.widget.Toast;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentMonthHistoryBinding;
import com.rs.keepcart.databinding.NewpaperSalesReportListBinding;
import com.rs.keepcart.databinding.NewssPaperListBinding;


public class MonthHistory extends Fragment {

    private FragmentMonthHistoryBinding viewBinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_month_history, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBinding.monthListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewBinding.monthListRecyclerView.setAdapter(new MonthHistoryAdapter(getActivity()));
        return view;
    }
    public class MonthHistoryAdapter extends RecyclerView.Adapter<MonthHistoryAdapter.ViewHolder>{
        private Context contextt;
        public MonthHistoryAdapter(FragmentActivity activity) {
            this.contextt = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newpaper_sales_report_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
           /* holder.viewBinding.userNextPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                   *//* if(fragment == null)
                    {
                        fragment = new CustomerScreen();
                        fragLoadMethod(fragment);
                    } else
                    {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);*//*
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return 10;
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
