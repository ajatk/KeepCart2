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
import com.rs.keepcart.databinding.FragmentNewsPaperListBinding;
import com.rs.keepcart.databinding.NewssPaperListBinding;


public class NewsPaperList extends Fragment {
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentNewsPaperListBinding viewBinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_paper_list, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBinding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBinding.userlistRecyclerView.setAdapter(new NewsPaperAdapter(getActivity()));
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ViewHolder>{
        private Context context;
        public NewsPaperAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newss_paper_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.viewBinding.userNextPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                    if(fragment == null)
                    {
                        fragment = new CustomerScreen();
                        fragLoadMethod(fragment);
                    } else
                    {
                        fragLoadMethod(fragment);
                    }
                    fragmentTransaction.addToBackStack(null);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private NewssPaperListBinding viewBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                viewBinding = DataBindingUtil.bind(itemView);
            }
        }
    }
    public void fragLoadMethod(Fragment fragment)
    {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = ((HomePage)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
