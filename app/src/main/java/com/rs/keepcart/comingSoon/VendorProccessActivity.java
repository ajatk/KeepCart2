package com.rs.keepcart.comingSoon;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityVendorProccessBinding;
import com.rs.keepcart.login.LoginActivity;
import com.rs.keepcart.login.SignUpModelClass;
import com.rs.keepcart.login.SignUpViewModelClass;
import com.rs.keepcart.magazines.SetMagazinesInfoClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorProccessActivity extends AppCompatActivity {
    private ActivityVendorProccessBinding viewBinding;
    private VendorProccessViewModel viewModel;
    private VendorStatusInfoModel infoClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this,R.layout.activity_vendor_proccess);
        //viewBinding.continueApp.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(VendorProccessViewModel.class);
//        viewModel.inject();
        viewModel.loginLiveData.observe(this,this::signUpResponse);
        String id_ = MySharedData.getGeneralSaveSession("userId");
        infoClass = new VendorStatusInfoModel(id_);
        viewModel.loginApii(infoClass);
    }

    private void signUpResponse(Resource<VendorStatusModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                String status = resource.data.getStatus();

                String activ_stats = MySharedData.getGeneralSaveSession("Act_status_1");



                    if (status.equals("200")) {

                            if (activ_stats.equals("0")) {
                                Intent inV0 = new Intent(getApplicationContext(), VendorProccessActivity.class);

                               // startActivity(inV0);

                            }else if( activ_stats.equals("1"))
                            {
                                Toast.makeText(getApplicationContext(), resource.data.getStatus(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK & Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }



                    } else if(status=="201") {

                        Snackbar.make(viewBinding.getRoot(), resource.data.getActiveStatus(), Snackbar.LENGTH_SHORT).show();
                    }



                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;

        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), VendorProccessActivity.class);
        startActivity(intent);


    }


}
