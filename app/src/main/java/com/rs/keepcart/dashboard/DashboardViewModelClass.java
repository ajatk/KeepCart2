package com.rs.keepcart.dashboard;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rs.keepcart.dashboard.dashBoardModel.DashBoardModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 6/11/2018.
 */

public class DashboardViewModelClass extends ViewModel {

    public MutableLiveData<Resource<DashBoardModelClass>> loginLiveData = new MutableLiveData<>();
    DashBoardModelClass infoModelClass;
    private ApiInterface apiInterface;

    void loginApiDash(String id_) {
        try {
             infoModelClass = new DashBoardModelClass();
            if(loginLiveData!=null) {
                loginLiveData.setValue(Resource.loading());
                if (apiInterface == null) {
                    apiInterface = ApiClient.getClient().create(ApiInterface.class);

                    Call<DashBoardModelClass> call = apiInterface.getDashBoard(id_);
                    call.enqueue(new Callback<DashBoardModelClass>() {
                        @Override
                        public void onResponse(Call<DashBoardModelClass> call, Response<DashBoardModelClass> response) {
                            try {
                                if (response.isSuccessful()) {
                                    System.out.println("what is this :" + response.body());

                                    DashBoardModelClass infoModelClass = response.body();
                                    //noinspection ConstantConditions
                                    if (response.body().getStatus() == 200) {

                                        addToSharedPreference(infoModelClass);
                                        System.out.println("what is the status :" + response.body().getStatus());
                                    }
                                    loginLiveData.setValue(Resource.success(infoModelClass, ""));

                                } else {
                                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                                }

                            } catch (Exception e) {
                                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<DashBoardModelClass> call, @NonNull Throwable t) {
                            loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                        }
                    });
                } else {
                    System.out.println("all is null thats it....");
                }
            }else{
                System.out.println("loginLiveData is null....");
            }
        } catch (Exception e) {
            System.out.println("what is this :" + e.getMessage());
        }

    }
    private void addToSharedPreference(DashBoardModelClass infoModelClass) {


        MySharedData.setGeneralSaveSession("mobile_no_dash", infoModelClass.getMobile());
        MySharedData.setGeneralSaveSession("email_dash", infoModelClass.getEmailid());
        MySharedData.setGeneralSaveSession("",infoModelClass.getUserid());
    }
}
