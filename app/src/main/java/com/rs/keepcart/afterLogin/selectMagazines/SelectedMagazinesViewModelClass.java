package com.rs.keepcart.afterLogin.selectMagazines;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rs.keepcart.afterLogin.SelectedInfoModelClass;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectedNewsPaperModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 6/5/2018.
 */

public class SelectedMagazinesViewModelClass extends ViewModel {

    public MutableLiveData<Resource<SelectedMagazineModelClass>> loginLiveData = new MutableLiveData<>();
    SelectedInfoModelClass infoModelClass;
    void loginApiSelect(SelectedInfoModelClass infoModelClass) {
        try {
            this.infoModelClass = infoModelClass;
            loginLiveData.setValue(Resource.loading());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SelectedMagazineModelClass> call = apiInterface.getSelectedMagazines(infoModelClass.getConvertedString(),infoModelClass.getReg_id());
            call.enqueue(new Callback<SelectedMagazineModelClass>() {
                @Override
                public void onResponse(@NonNull Call<SelectedMagazineModelClass> call, @NonNull Response<SelectedMagazineModelClass> response) {
                    try {
                        if (response.isSuccessful()) {
                            System.out.println("what is this :"+response.body());

                            SelectedMagazineModelClass modelClass = response.body();
                            if (modelClass.getStatus() == 200) {

                            }
                           loginLiveData.setValue(Resource.success(modelClass, ""));
                        } else {
                            loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                        }

                    } catch (Exception e) {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                }

                @Override
                public void onFailure(Call<SelectedMagazineModelClass> call, Throwable t) {
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
