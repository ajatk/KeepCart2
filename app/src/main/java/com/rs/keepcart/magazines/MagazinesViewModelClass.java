package com.rs.keepcart.magazines;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/18/2018.
 */

public class MagazinesViewModelClass extends ViewModel {
    MutableLiveData<Resource<MagazinesModelClass>> loginLiveData = new MutableLiveData<>();
    SetMagazinesInfoClass infoClass ;

    public void loginApi(SetMagazinesInfoClass infoClass) {
       this.infoClass = infoClass;
        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MagazinesModelClass> call = apiInterface.getMagazines(infoClass.getVendorid());
        call.enqueue(new Callback<MagazinesModelClass>() {
            @Override
            public void onResponse(Call<MagazinesModelClass> call, Response<MagazinesModelClass> response) {
                try
                {
                    if(response.isSuccessful())
                    {
                        MagazinesModelClass magazinesModelClass = response.body();
                        if(magazinesModelClass.getStatus()==200)
                        {
//                            addToSharedPreference(magazinesModelClass);
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
            public void onFailure(Call<MagazinesModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });

    }

}
