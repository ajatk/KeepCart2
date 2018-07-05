package com.rs.keepcart.newsPaperList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.model.SetNewspaperInfoModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 5/17/2018.
 */

public class NewsPaperViewModelClass extends ViewModel {

   public MutableLiveData<Resource<NewsPaperModelClass>> loginLiveData = new MutableLiveData<>();
    SetNewspaperInfoModelClass infoModelClass;
    public void loginApi(SetNewspaperInfoModelClass infoModelClass) {
        this.infoModelClass = infoModelClass;
        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsPaperModelClass> call = apiInterface.getNewspaper(infoModelClass.getId());
        call.enqueue(new Callback<NewsPaperModelClass>() {
            @Override
            public void onResponse(Call<NewsPaperModelClass> call, Response<NewsPaperModelClass> response) {
                try
                {
                    if(response.isSuccessful())
                    {
                        NewsPaperModelClass newsPaperModelClass = response.body();
                        if(newsPaperModelClass.getStatus()==200)
                        {

                        }
                       loginLiveData.setValue(Resource.success(newsPaperModelClass,""));
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
            public void onFailure(Call<NewsPaperModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });

    }
    private void addToSharedPreference(SetNewspaperInfoModelClass infoModelClass) {
        /*MySharedData.setGeneralSaveSession("Id",loginModelClass.getEmailid());
        MySharedData.setGeneralSaveSession("PhoneNo",loginModelClass.getPhoneNo());

        MySharedData.setGeneralSaveSession("userId",loginInfoModel.getPassword());
        MySharedData.setGeneralSaveSession("pass",loginInfoModel.getUserId());*/
    }

}
