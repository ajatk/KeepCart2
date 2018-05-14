package com.rs.keepcart.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rs.keepcart.HomePage;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.UserListDesignBinding;
import com.rs.keepcart.fragments.UserStatus;

/**
 * Created by sam on 4/30/2018.
 */

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.ViewHolder> /*implements View.OnClickListener*/ {
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private Context context;
    private int count=0;
    public UserListRecyclerAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_design,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       holder.viewBinding.userImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
               if(fragment == null)
               {
                   fragment = new UserStatus();
                   fragLoadMethod(fragment);
               } else
               {
                   fragLoadMethod(fragment);
               }
           }
       });
        holder.viewBinding.userlistLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                count++;
                if(count==1)
                {
                    holder.viewBinding.checkBox.setVisibility(View.VISIBLE);
                }else if(count==2)
                {
                    holder.viewBinding.checkBox.setVisibility(View.GONE);
                    count=0;
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

   /* @Override
    public void onClick(View v) {
        Toast.makeText(context, "position"+, Toast.LENGTH_SHORT).show();
        switch (v.getId())
        {
            case R.id.userlist_layout:
                if(fragment == null)
                {
                    fragment = new UserStatus();
                    fragLoadMethod(fragment);
                } else
                {
                    fragLoadMethod(fragment);
                }

        }
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout userLayout;
        private UserListDesignBinding viewBinding;
        public ViewHolder(View itemView) {
            super(itemView);
           viewBinding = DataBindingUtil.bind(itemView);
           //viewBinding.
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
