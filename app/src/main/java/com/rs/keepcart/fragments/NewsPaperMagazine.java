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

import com.rs.keepcart.HomePage;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentNewsPaperMagazineBinding;
import com.rs.keepcart.databinding.UserMonthStatusBinding;

public class NewsPaperMagazine extends Fragment {

   private FragmentNewsPaperMagazineBinding viewBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper_magazine, container, false);
        viewBinding = DataBindingUtil.bind(view);
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.userlistRecyclerView.setAdapter(new StatusAdapter(getActivity()));
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
        private Context context;
        public StatusAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_month_status,parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

          holder.viewBinding.monthYear.setText("Newspaper");
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private UserMonthStatusBinding viewBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

}
