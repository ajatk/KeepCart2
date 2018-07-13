package com.rs.keepcart.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginViewModel extends ViewModel {

    MutableLiveData<Resource<LoginModelClass>> loginLiveData = new MutableLiveData<>();
    LoginInfoModel loginInfoModel;

    public void loginApii(LoginInfoModel loginInfoModel) {
        this.loginInfoModel = loginInfoModel;
        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModelClass> call = apiInterface.getLoginCall(loginInfoModel.getVendor_Email(),
                loginInfoModel.getPassword() /*loginInfoModel.getAccountNo()),
                loginInfoModel.getBankName()*/);
        call.enqueue(new Callback<LoginModelClass>() {
            @Override
            public void onResponse(Call<LoginModelClass> call, Response<LoginModelClass> response) {
                try {
                    if (response.isSuccessful()) {
                        LoginModelClass loginModelClass = response.body();
                        if (loginModelClass.getStatus() == 200) {
                            addToSharedPreference(loginInfoModel);

                            MySharedData.setGeneralSaveSession("userId",loginModelClass.getVendorID());
                            MySharedData.setGeneralSaveSession("vendor_Nam",loginModelClass.getVendorName());
                        }
                        loginLiveData.setValue(Resource.success(loginModelClass, ""));
                    } else {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                } catch (Exception e) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            }

            @Override
            public void onFailure(Call<LoginModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });
    }

    private void addToSharedPreference(LoginInfoModel loginInfoModel) {
        /*MySharedData.setGeneralSaveSession("Id",loginModelClass.getEmailid());
        MySharedData.setGeneralSaveSession("PhoneNo",loginModelClass.getPhoneNo());*/


        MySharedData.setGeneralSaveSession("pass",loginInfoModel.getPassword());
        MySharedData.setGeneralSaveSession("vender_Email",loginInfoModel.getVendor_Email());

    }
}
