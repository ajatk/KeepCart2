package com.rs.keepcart.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rs.keepcart.HomePage;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.CustomerListDesignBinding;
import com.rs.keepcart.databinding.FragmentCustomerScreenBinding;
import com.rs.keepcart.databinding.FragmentNewsPaperListBinding;
import com.rs.keepcart.databinding.NewssPaperListBinding;

public class CustomerScreen extends Fragment {
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentCustomerScreenBinding viewBinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_screen, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.userlistRecyclerView.setAdapter(new NewsPaperCustomerAdapter(getActivity()));
        return view;
    }
    public void fragLoadMethod(Fragment fragment)
    {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = ((HomePage)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
    public class NewsPaperCustomerAdapter extends RecyclerView.Adapter<NewsPaperCustomerAdapter.ViewHolder>{
        private Context context;
        public NewsPaperCustomerAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        @Override
        public NewsPaperCustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_design,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.viewBinding.customerlistLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                    /*if(fragment == null)
                    {
                        fragment = new NewsPaperMagazine();
                        fragLoadMethod(fragment);
                    } else
                    {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);*/
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private CustomerListDesignBinding viewBinding;
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
