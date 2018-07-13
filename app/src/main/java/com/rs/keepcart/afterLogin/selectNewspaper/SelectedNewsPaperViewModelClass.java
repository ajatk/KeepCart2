package com.rs.keepcart.afterLogin.selectNewspaper;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rs.keepcart.afterLogin.SelectedInfoModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 6/2/2018.
 */

public class SelectedNewsPaperViewModelClass extends ViewModel {

    public MutableLiveData<Resource<SelectedNewsPaperModelClass>> loginLiveData = new MutableLiveData<>();
    SelectedInfoModelClass jsonArray;

    void loginApiSelect(SelectedInfoModelClass infoModelClass) {
        try {
            this.jsonArray = infoModelClass;
            loginLiveData.setValue(Resource.loading());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//            Call<Task> call = taskService.createTask(task);
            Call<SelectedNewsPaperModelClass> call = apiInterface.getSelectedNewsPaper(infoModelClass.getReg_id(),infoModelClass.getConvertedString());
            call.enqueue(new Callback<SelectedNewsPaperModelClass>() {
                @Override
                public void onResponse(@NonNull Call<SelectedNewsPaperModelClass> call, @NonNull Response<SelectedNewsPaperModelClass> response) {
                    try {
                        if (response.isSuccessful()) {

                            System.out.println("what is this :"+response.body());

                            SelectedNewsPaperModelClass newsPaperModelClass = response.body();
                            if (response.body().getStatus() == 200) {

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
                public void onFailure(Call<SelectedNewsPaperModelClass> call, Throwable t) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            });
        }
        catch (Exception e)
        {
            System.out.println("what is this :"+e.getMessage());
        }

    }
}
