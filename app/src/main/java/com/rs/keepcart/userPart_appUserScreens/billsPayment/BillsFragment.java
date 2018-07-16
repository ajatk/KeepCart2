package com.rs.keepcart.userPart_appUserScreens.billsPayment;

import android.annotation.SuppressLint;
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
import android.widget.Filter;
import android.widget.Filterable;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.BillsListDesignBinding;
import com.rs.keepcart.databinding.FragmentBillsBinding;
import com.rs.keepcart.databinding.FragmentUserMagazineListBinding;
import com.rs.keepcart.databinding.MagazinesListDesignBinding;
import com.rs.keepcart.userPart_appUserScreens.shopAndExtra.UserMagazineListFragment;

public class BillsFragment extends Fragment {

    private FragmentBillsBinding viewBinding;
    private  Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bills, container, false);

        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();

        viewBinding.billsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.billsRecyclerView.setAdapter(new BillsFragmentAdapter(context));
        return view;
    }

    public class BillsFragmentAdapter extends RecyclerView.Adapter<BillsFragmentAdapter.ViewHolder> implements Filterable {
        private Context context;



        public BillsFragmentAdapter(Context activity) {
            this.context = activity;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bills_list_design, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 10/*collectionListD.size()*/;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private BillsListDesignBinding viewbinding;

            public ViewHolder(View itemView) {
                super(itemView);
               viewbinding = DataBindingUtil.bind(itemView);
            }
        }

        @Override
        public Filter getFilter() {

            return new Filter() {
                FilterResults filterResults;
                String charString;

                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    charString = charSequence.toString();
                    filterResults = new FilterResults();
                    // filtermagzinesDetail = magzinesDetail;
                    //viewBinding.searchViewUser.
                  /*  if (charString.isEmpty()) {

                        filterResults.values = magzinesDetaillis;

                    } else {

                        List<MagzinesDetail> filteredList = new ArrayList<>();

                        for (MagzinesDetail state : magzinesDetaillis) {

                            if (state.getName().toLowerCase().contains(charString)) {
                                filteredList.add(state);
                            }
                        }

                        filtermagzinesDetail = filteredList;

                        filterResults.values = filteredList;
                        filterResults.count = filteredList.size();
                    }*/
                    return filterResults;
                }


                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    //    filtermagzinesDetail = (List<MagzinesDetail>) filterResults.values;

                    notifyDataSetChanged();
                }
            };
        }
    }

}
