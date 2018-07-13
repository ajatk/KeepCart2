package com.rs.keepcart.vendorUserList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/18/2018.
 */

public class UserViewModelClass extends ViewModel {
    public MutableLiveData<Resource<UserListModelClass>> loginLiveData = new MutableLiveData<>();
    SetNewspaperInfoModelClass infoModelClass;
    public void loginApiUser(SetNewspaperInfoModelClass infoModelClass) {
        this.infoModelClass = infoModelClass;
        loginLiveData.setValue(Resource.loading());
        List<UserDetail> userDetailsList = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserListModelClass> call = apiInterface.getUsers(infoModelClass.getId());
        call.enqueue(new Callback<UserListModelClass>() {
            @Override
            public void onResponse(Call<UserListModelClass> call, Response<UserListModelClass> response) {
                try
                {
                    if(response.isSuccessful())
                    {
                        UserListModelClass userListModelClass = response.body();
                        if(userListModelClass.getStatus()==200)
                        {
                            addToSharedPreference(infoModelClass);

                            userDetailsList.addAll(userListModelClass.getUserDetails());
                        }
                       //addToSharedPreference1(infoModelClass);
                       loginLiveData.setValue(Resource.success(userListModelClass,""));
                    }else
                    {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }

                }catch (Exception e)
                {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            }

            @Override
            public void onFailure(Call<UserListModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });

    }

    private void addToSharedPreference(SetNewspaperInfoModelClass infoModelClass) {

          MySharedData.setGeneralSaveSession("userAddress",String.valueOf(infoModelClass.getAddress()));
          MySharedData.setGeneralSaveSession("userBill",infoModelClass.getBillAmount());
          MySharedData.setGeneralSaveSession("userServicetype",infoModelClass.getServiceType());
          MySharedData.setGeneralSaveSession("userImag",String.valueOf(infoModelClass.getUserimage()));
          MySharedData.setGeneralSaveSession("venderNam",String.valueOf(infoModelClass.getVendorName()));
    }
}
