package com.rs.keepcart.vendorUserList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.rs.keepcart.R;
import com.rs.keepcart.adapter.UserListViewpagerAdapter;
import com.rs.keepcart.databinding.FragmentUserListBinding;
import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {


    private FragmentUserListBinding viewbinding;
    private Toolbar toolbar;
    private Context context;
    private TextView title;
    private ImageView  back_button;
    private List<UserDetail> userDetail;
    private  UserListViewpagerAdapter viewpagerAdapter;
    private UserViewModelClass viewModel;
    private SetNewspaperInfoModelClass infoClass;
    private ArrayList <Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding = DataBindingUtil.setContentView(this, R.layout.fragment_user_list);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);

        back_button = findViewById(R.id.back_button);
        userDetail = new ArrayList<>();
        fragmentList = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(UserViewModelClass.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);
        String id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new SetNewspaperInfoModelClass(id_);
        viewModel.loginApiUser(infoClass);
        title = findViewById(R.id.page_title);
        title.setText(R.string.user_toolbar_title);
        adapterMethod();
    }
    public void adapterMethod()
    {
        fragmentList.add(new NewUser());
        fragmentList.add(new NewUser());
        fragmentList.add(new NewUser());
        viewbinding.userTabLayout.setupWithViewPager(viewbinding.userViewPager);
        viewpagerAdapter = new UserListViewpagerAdapter(viewbinding.userViewPager,getApplicationContext(),getSupportFragmentManager(), fragmentList);
        viewbinding.userViewPager.setAdapter(viewpagerAdapter);
    }
    private void loginResponse(Resource<UserListModelClass> resource) {
        viewbinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewbinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();

                switch (status) {
                    // NewsPaperModelClass newsPaperModelClass = response.body();
                    case 200:
                        userDetail = resource.data.getUserDetails();
                        // magzinesDetail.addAll(resource.data.getMagzinesDetails());
                        if(userDetail!=null)
                        {
                            ((NewUser)viewpagerAdapter.getItem(0)).setUserDetail(userDetail);
                        }
                        break;
                }
                //Toast.makeText(context, resource.data.get, Toast.LENGTH_SHORT).show();
                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewbinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }

    }
    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount()>0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();

    }


    @Override
    public void onStart() {
        super.onStart();
        // where mText is the title you want on your toolbar/actionBar
        title.setText(R.string.user_toolbar_title);
    }
    public void backButton(View view){
        if (getFragmentManager().getBackStackEntryCount()>0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

}
