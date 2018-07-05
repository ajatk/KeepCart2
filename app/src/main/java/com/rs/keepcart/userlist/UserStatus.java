package com.rs.keepcart.userlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentUserStatusBinding;
import com.rs.keepcart.databinding.UserMonthStatusBinding;
import com.rs.keepcart.salesReport.NewsPaperMagazine;


public class UserStatus extends Fragment/* implements View.OnClickListener*/{
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentUserStatusBinding viewBindding;
    private Context context;
    private TextView titleSet;
    private ImageView back_button;
    private NewUser newUser;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_status, container, false);
        viewBindding = DataBindingUtil.bind(view);
        context = getActivity();
        assert (getActivity()) != null;
        back_button = ((UserListActivity)getActivity()).findViewById(R.id.back_button);
        viewBindding.userlistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewBindding.userlistRecyclerView.setAdapter(new UserStatusAdapter(getActivity(), getActivity().getSupportFragmentManager()));
        //back_button.setOnClickListener(this);

        toolbarTilte();
        return view;
    }

    public void toolbarTilte() {

        titleSet = ((UserListActivity) getActivity()).findViewById(R.id.page_title);
        titleSet.setText(R.string.user_status);
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
              if(newUser==null)
              {
                  newUser = new NewUser();
                  fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.frame1, newUser);
              } else
              {
                  fragmentTransaction.show(newUser);
              } fragmentTransaction.addToBackStack(null).commit();
                break;
        }
    }*/
    public  void onBackButtonclick()
    {
       /* back_button.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((keyCode==KeyEvent.KEYCODE_BACK)*//*&& back_button.canGoBack()*//*){
                    if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0)
                    {
                       context.finish();

                        return false;
                    }
                    else
                    {
                        getSupportFragmentManager().popBackStack();
                        removeCurrentFragment();

                        return false;
                    }
                }
                return false;
            }
        });*/


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class UserStatusAdapter extends RecyclerView.Adapter<UserStatusAdapter.ViewHolder> {
        private Context context;
        private FragmentManager fm;

        public UserStatusAdapter(FragmentActivity activity) {
            this.context = activity;
        }

        public UserStatusAdapter(FragmentActivity activity, FragmentManager fragmentManager) {
            this.context = activity;
            this.fm = fragmentManager;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_month_status, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.viewBinding.userStatusLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                    if (fragment == null) {
                        fragment = new NewsPaperMagazine();
                        fragLoadMethod(fragment);
                    } else {
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

    public void fragLoadMethod(Fragment fragment) {
        // fragmentTransaction = (Activity)context.getApplicationContext().
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.frame, fragment);
        fragmentTransaction.replace(R.id.frame1, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

}
