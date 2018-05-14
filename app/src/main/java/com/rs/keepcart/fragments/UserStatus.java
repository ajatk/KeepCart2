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
import com.rs.keepcart.databinding.FragmentUserStatusBinding;
import com.rs.keepcart.databinding.UserMonthStatusBinding;

public class UserStatus extends Fragment {
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
   private FragmentUserStatusBinding viewBindding;
   private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_status, container, false);
        viewBindding = DataBindingUtil.bind(view);
        context = getActivity();
        viewBindding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBindding.userlistRecyclerView.setAdapter(new UserStatusAdapter(getActivity()));
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class UserStatusAdapter extends RecyclerView.Adapter<UserStatusAdapter.ViewHolder>{
         private Context context;
        public UserStatusAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_month_status,parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.viewBinding.userStatusLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                    if(fragment == null)
                    {
                        fragment = new NewsPaperMagazine();
                        fragLoadMethod(fragment);
                    } else
                    {
                        fragLoadMethod(fragment);
                    }
                }
            });
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
    public void fragLoadMethod(Fragment fragment)
    {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = ((HomePage)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
