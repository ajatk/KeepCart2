package com.rs.keepcart.comingSoon;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rs.keepcart.login.SignUp;
import com.rs.keepcart.login.SignUpModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/29/2018.
 */

public class VendorProccessViewModel extends ViewModel {
    public MutableLiveData<Resource<VendorStatusModelClass>> loginLiveData = new MutableLiveData<>();
    VendorStatusInfoModel statusInfoModel;
    VendorStatusModelClass modelClass;
    public String TAG = VendorProccessViewModel.class.getSimpleName();
    String id_ = MySharedData.getGeneralSaveSession("vend_id");
    public void loginApii(VendorStatusInfoModel statusInfoModel ) {
        try{
          this.statusInfoModel = statusInfoModel;
            loginLiveData.setValue(Resource.loading());

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<VendorStatusModelClass> call = apiInterface.getStateus(statusInfoModel.getVendorID());
            call.enqueue(new Callback<VendorStatusModelClass>() {
                @Override
                public void onResponse(@NonNull Call<VendorStatusModelClass> call, @NonNull Response<VendorStatusModelClass> response) {
                    try {
                        if (response.isSuccessful()) {
                            modelClass  = response.body();

                            addToSharedPreference1(modelClass);

                            loginLiveData.setValue(Resource.success(response.body(), ""));
                        } else {
                            loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                        }
                    } catch (Exception e) {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<VendorStatusModelClass> call, @NonNull Throwable t) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            });
        }
        catch (Exception e){
            System.out.println("hwat is sexception :"+e.getMessage());
        }
    }
    private void addToSharedPreference1(VendorStatusModelClass modelClass )
    {
        MySharedData.setGeneralSaveSession("Act_status_1", String.valueOf(modelClass.getActiveStatus()));
        MySharedData.setGeneralSaveSession("status_0", String.valueOf(modelClass.getStatus()));

    }
}
