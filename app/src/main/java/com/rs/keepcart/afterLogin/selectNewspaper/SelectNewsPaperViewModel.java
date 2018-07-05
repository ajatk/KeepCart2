package com.rs.keepcart.afterLogin.selectNewspaper;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.afterLogin.SelectedInfoModelClass;
import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/30/2018.
 */

public class SelectNewsPaperViewModel extends ViewModel {
    public MutableLiveData<Resource<SelectNewsPaperModelClass>> loginLiveData = new MutableLiveData<>();
    SelectedInfoModelClass infoModelClass;
    public void loginApi(SelectedInfoModelClass infoModelClass) {
        try {
            this.infoModelClass = infoModelClass;
            loginLiveData.setValue(Resource.loading());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SelectNewsPaperModelClass> call = apiInterface.getSelectNewsPaper(infoModelClass.getId());
            call.enqueue(new Callback<SelectNewsPaperModelClass>() {
                @Override
                public void onResponse(Call<SelectNewsPaperModelClass> call, Response<SelectNewsPaperModelClass> response) {
                    try {
                        if (response.isSuccessful()) {
                            SelectNewsPaperModelClass newsPaperModelClass = response.body();
                            if (newsPaperModelClass.getStatus() == 200) {

                            }
                            loginLiveData.setValue(Resource.success(newsPaperModelClass, ""));
                        } else {
                            loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                        }

                    } catch (Exception e) {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                }

                @Override
                public void onFailure(Call<SelectNewsPaperModelClass> call, Throwable t) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));

                }
            });

        }catch(Exception e) {
            System.out.println("what is the value of :"+e.getMessage());
        }

    }
}
