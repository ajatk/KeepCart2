package com.rs.keepcart.afterLogin.selectMagazines;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.magazines.SetMagazinesInfoClass;
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

public class SelectMagazinesViewModel extends ViewModel {
    MutableLiveData<Resource<SelectMagazinesModelClass>> loginLiveData = new MutableLiveData<>();
    SetMagazinesInfoClass infoClass ;

    public void loginApi(SetMagazinesInfoClass infoClass) {
        this.infoClass = infoClass;
        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SelectMagazinesModelClass> call = apiInterface.getSelectMagazines(infoClass.getVendorid());
        call.enqueue(new Callback<SelectMagazinesModelClass>() {
            @Override
            public void onResponse(Call<SelectMagazinesModelClass> call, Response<SelectMagazinesModelClass> response) {
                try
                {
                    if(response.isSuccessful())
                    {
                        SelectMagazinesModelClass magazinesModelClass = response.body();
                        if(magazinesModelClass.getStatus()==200)
                        {
                            //addToSharedPreference(magazinesModelClass);
                        }
                        loginLiveData.setValue(Resource.success(magazinesModelClass,""));
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
            public void onFailure(Call<SelectMagazinesModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });

    }

}
