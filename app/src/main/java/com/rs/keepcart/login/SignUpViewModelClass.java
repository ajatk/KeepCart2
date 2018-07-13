package com.rs.keepcart.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/25/2018.
 */

public class SignUpViewModelClass extends ViewModel {
    public MutableLiveData<Resource<SignUpModelClass>> loginLiveData = new MutableLiveData<>();
    SignUpInfoClass loginInfoModel;
    SignUpModelClass infoModelClas;

    public void loginApii(String accountType,String name_, String email_,String password_,
                          String mobileNo_,String latitude,String longitude,String jobOccupation_) {
        try{
//        this.loginInfoModel = loginInfoModel;
            loginInfoModel = new SignUpInfoClass(accountType,mobileNo_,name_,email_,password_,latitude,longitude,jobOccupation_);

        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SignUpModelClass> call = apiInterface.getSignUp(accountType,mobileNo_,name_,email_,password_,latitude,longitude,jobOccupation_);
        call.enqueue(new Callback<SignUpModelClass>() {
            @Override
            public void onResponse(@NonNull Call<SignUpModelClass> call, @NonNull Response<SignUpModelClass> response) {
                try {
                    infoModelClas = response.body();
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            addToSharedPreference(loginInfoModel);
                            addToSharedPreference1(infoModelClas);
                        }
                        loginLiveData.setValue(Resource.success(response.body(), ""));
                    } else {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                } catch (Exception e) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignUpModelClass> call, @NonNull Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });
    }
    catch (Exception e){
            System.out.println("hwat is sexception :"+e.getMessage());
        }
    }


    private void addToSharedPreference(SignUpInfoClass infoModelClass) {


        MySharedData.setGeneralSaveSession("password_s",infoModelClass.getPassword());
        MySharedData.setGeneralSaveSession("account_Type",infoModelClass.getAcountType());
        MySharedData.setGeneralSaveSession("mobile_No",String.valueOf(infoModelClass.getMobileNo()));
        MySharedData.setGeneralSaveSession("vender_Nam",String.valueOf(infoModelClass.getName()));
        MySharedData.setGeneralSaveSession("vender_Email",String.valueOf(infoModelClass.getEmail()));

    }
    private void addToSharedPreference1(SignUpModelClass infoModelClas) {


        MySharedData.setGeneralSaveSession("Act_status1", String.valueOf(infoModelClas.getActiveStatus()));
        MySharedData.setGeneralSaveSession("userId",infoModelClas.getVendorid());
        MySharedData.setGeneralSaveSession("mob_No",infoModelClas.getPhoneNo());
        MySharedData.setGeneralSaveSession("status0", String.valueOf(infoModelClas.getStatus()));
        MySharedData.setGeneralSaveSession("vend_name",infoModelClas.getVendorname());

    }
}
