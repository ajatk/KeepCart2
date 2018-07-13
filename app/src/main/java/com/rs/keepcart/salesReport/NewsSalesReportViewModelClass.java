package com.rs.keepcart.salesReport;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rs.keepcart.magazines.SetMagazinesInfoClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.salesReport.newspaperSales.NewspaperSalesModelClass;
import com.rs.keepcart.utills.ApplicationConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 6/17/2018.
 */

public class NewsSalesReportViewModelClass extends ViewModel {

    public MutableLiveData<Resource<NewspaperSalesModelClass>> loginLiveData = new MutableLiveData<>();
    SetMagazinesInfoClass infoClass;
    NewspaperSalesModelClass  infoModelClas;

    public void loginApiSales(String id_) {
        try{
//        this.loginInfoModel = loginInfoModel;
           // infoClass = new SetMagazinesInfoClass(accountType,mobileNo_,name_,email_,password_,latitude,longitude,jobOccupation_);

            loginLiveData.setValue(Resource.loading());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NewspaperSalesModelClass> call = apiInterface.getNewsSalesReport(id_);
            call.enqueue(new Callback<NewspaperSalesModelClass>() {
                @Override
                public void onResponse(@NonNull Call<NewspaperSalesModelClass> call, @NonNull Response<NewspaperSalesModelClass> response) {
                    try {
                       infoModelClas = response.body();
                        if (response.isSuccessful()) {
                            if (response.body().getStatus() == 200) {
                                System.out.println("what is the status :" + response.body().getStatus());

                            }
                            loginLiveData.setValue(Resource.success(response.body(), ""));
                        } else {
                            loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                        }
                    } catch (Exception e) {
                        loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewspaperSalesModelClass> call, @NonNull Throwable t) {
                    loginLiveData.setValue(Resource.message(ApplicationConstants.ERROR_MSG));
                }
            });
        }
        catch (Exception e){
            System.out.println("hwat is sexception :"+e.getMessage());
        }
    }
}
