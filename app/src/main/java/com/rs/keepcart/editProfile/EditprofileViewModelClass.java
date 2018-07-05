package com.rs.keepcart.editProfile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rs.keepcart.editProfile.profileModel.EditProfileModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 6/21/2018.
 */

public class EditprofileViewModelClass extends ViewModel {

    MutableLiveData<Resource<EditProfileModelClass>> loginLiveData = new MutableLiveData<>();
    CityAndBankModelClass infoModelClass;

    public void loginApiEditProfile( CityAndBankModelClass infoClass) {
       this.infoModelClass = infoClass;
        loginLiveData.setValue(Resource.loading());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<EditProfileModelClass> call = apiInterface.getEditProfile(/*cityN,bankN,email_,
                name_,
                mobileNo_,
                address_,accountNo_,ifscCode_,
                sector_,panCard, adharCard, pinCode_*/
              infoClass.getCity(),infoClass.getBank(),infoClass.getEmail_(),infoClass.getName_(),
                infoClass.getMobileNo_(), infoClass.getAddress_(),infoClass.getAccountNo_()
                ,infoClass.getIfscCode_() ,infoClass.getSector_(),infoClass.getPanCard(),  infoClass.getAdharCard(),infoClass.getPinCode_()
               );
        call.enqueue(new Callback<EditProfileModelClass>() {
            @Override
            public void onResponse(Call<EditProfileModelClass> call, Response<EditProfileModelClass> response) {
                try
                {
                    if(response.isSuccessful())
                    {
                        EditProfileModelClass modelClass = response.body();
                        if(modelClass.getStatus()==200)
                        {
                            addToSharedPreference( infoModelClass);
                        }
                        loginLiveData.setValue(Resource.success(modelClass,""));
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
            public void onFailure(Call<EditProfileModelClass> call, Throwable t) {
                loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
            }
        });

    }
    private void addToSharedPreference(CityAndBankModelClass infoModelClass) {


        MySharedData.setGeneralSaveSession("name_p",infoModelClass.getName_());
        MySharedData.setGeneralSaveSession("address_p",infoModelClass.getAddress_());
        MySharedData.setGeneralSaveSession("mobile_No_p",String.valueOf(infoModelClass.getMobileNo_()));
        MySharedData.setGeneralSaveSession("bank_p",String.valueOf(infoModelClass.getBank()));
        MySharedData.setGeneralSaveSession("account_no_p",String.valueOf(infoModelClass.getAccountNo_()));
        MySharedData.setGeneralSaveSession("ifsc_p",String.valueOf(infoModelClass.getIfscCode_()));
        MySharedData.setGeneralSaveSession("pincode_p",String.valueOf(infoModelClass.getPinCode_()));
        MySharedData.setGeneralSaveSession("city_p",String.valueOf(infoModelClass.getCity()));
        MySharedData.setGeneralSaveSession("sector_p",String.valueOf(infoModelClass.getSector_()));
        MySharedData.setGeneralSaveSession("pancard_p",String.valueOf(infoModelClass.getPanCard()));
        MySharedData.setGeneralSaveSession("adharcard_p",String.valueOf(infoModelClass.getAdharCard()));

    }
}
