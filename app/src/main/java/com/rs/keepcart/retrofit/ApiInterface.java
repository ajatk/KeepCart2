package com.rs.keepcart.retrofit;


import com.rs.keepcart.afterLogin.selectMagazines.SelectMagazinesModelClass;
import com.rs.keepcart.afterLogin.selectMagazines.SelectedMagazineModelClass;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectNewsPaperModelClass;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectedNewsPaperModelClass;
import com.rs.keepcart.dashboard.dashBoardModel.DashBoardModelClass;
import com.rs.keepcart.editProfile.profileModel.EditProfileImageModelClass;
import com.rs.keepcart.editProfile.profileModel.EditProfileModelClass;
import com.rs.keepcart.editProfile.profileModel.GetDataProfileModelClass;
import com.rs.keepcart.editProfile.profileModel.Profile;
import com.rs.keepcart.login.LoginModelClass;
import com.rs.keepcart.login.SignUpModelClass;
import com.rs.keepcart.comingSoon.VendorStatusModelClass;
import com.rs.keepcart.magazines.MagazinesModelClass;
import com.rs.keepcart.newsPaperList.NewsPaperModelClass;
import com.rs.keepcart.paytmSet.Checksum;
import com.rs.keepcart.paytmSet.OnTransactionResponseModelClass;
import com.rs.keepcart.paytmSet.TransactionResponseElementsModelClass;
import com.rs.keepcart.salesReport.newspaperSales.NewspaperSalesModelClass;
import com.rs.keepcart.userlist.UserListModelClass;
import com.rs.keepcart.wallet.walletModelClass.WalletModelClass;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by sam on 4/10/2018.
 */

public interface ApiInterface {

//    @FormUrlEncoded
//    @POST("user/signup")
//    Call<SignUpModelClass> getRegisterCall(@Field("name") String name_, @Field("email") String email_, @Field("password") String password_, @Field("mobileNo") String mobileNo_, @Field("login_type") String login_type, @Field("social_id") String Social_id);

    @FormUrlEncoded
    @POST("apis/vendor/login")
    Call<LoginModelClass> getLoginCall(@Field("email") String id, @Field("pass") String password_
                                       /*@Field("login_type") String login_type, @Field("social_id") String Social_id*/);

    @GET("apis/vendor/getnewspaper")
    Call<NewsPaperModelClass> getNewspaper(@Query("id") String id);

    @GET("apis/vendor/getmagzines")
    Call<MagazinesModelClass> getMagazines(@Query("id") String vendorid);

    @GET("apis/vendor/getuser")
    Call<UserListModelClass> getUsers(@Query("id") String id);

    /*usertype_id=2&mobileno=7775755&name=rahul&email=namensnewl@gmail.com&pass=3hdfgjkg252dew&latitude=0.0&longitude=0.0&jobs=vendor*/
    @GET("apis/vendor/RegisterUser")
    Call<SignUpModelClass> getSignUp(
            @Query("usertype_id") String acountType,
                                     @Query("mobileno") String mobileNo,
                                     @Query("name") String name,
                                     @Query("email") String email,
                                     @Query("pass") String password,
                                     @Query("latitude") String lat,
                                     @Query("longitude") String lon,
                                     @Query("jobs") String jobOccupation);

    @GET("apis/vendor/status")

   Call<VendorStatusModelClass> getStateus(@Query("vendor_id")String id);

    @FormUrlEncoded
    @POST ("apis/vendor/newspapers")
    Call<SelectNewsPaperModelClass>getSelectNewsPaper(@Field("id") String id);

    @FormUrlEncoded
    @POST ("apis/vendor/Vendornewspaper")
    Call<SelectedNewsPaperModelClass>getSelectedNewsPaper(@Field("reg_id")String reg_id,
                                                          @Field("newspaper_id")String newspapers_id);

    @FormUrlEncoded
    @POST ("apis/vendor/Vendormagzines")
    Call<SelectedMagazineModelClass> getSelectedMagazines(@Field("magzine_id")String convertedString,
                                                          @Field("reg_id")String reg_id);

    @FormUrlEncoded
    @POST ("apis/vendor/magazines")
    Call<SelectMagazinesModelClass>getSelectMagazines(@Field("id") String id);

    @FormUrlEncoded
    @POST("apis/vendor/SalesNewspaper")
    Call<NewspaperSalesModelClass> getNewsSalesReport(@Field("vendorid") String id);

    @Multipart
    @POST("apis/Vendor/Image")
    Call<EditProfileImageModelClass> getUploadImage(@Part("id") RequestBody id_s, @Part MultipartBody.Part body  );

    @FormUrlEncoded
    @POST("apis/Vendor/Sendprofile")
    Call<GetDataProfileModelClass> getProfileData(@Field("id") String id_s  );

    @GET ("apis/home/getCategory")
    Call<DashBoardModelClass>getDashBoard(@Query("vendor_id")String id );

///http://www.uicreations.com/keepkart/apis/payment/generateChecksum
    //ORDER_ID,TXN_AMOUNT,CUST_ID,EMAIL,MOBILE_NO
    @FormUrlEncoded
    @POST("apis/payment/generateChecksum")
    Call<Checksum> getChecksum(
            @Field("ORDER_ID")  String order_id,
            @Field("TXN_AMOUNT") String txn_amount,
            @Field("CUST_ID") String cust_id,
            @Field("EMAIL") String email,
            @Field("MOBILE_NO") String mobile_no);

    //cityN,bankN,email_,    name_,    mobileNo_,    address_,accountNo_,ifscCode_,    sector_,panCard, adharCard, pinCode_

    @FormUrlEncoded
    @POST("apis/Vendor/editprofile")
    Call<EditProfileModelClass> getEditProfile(
            @Field("city")String city,
            @Field("bank_name") String bank,
            @Field("email")String email_,
            @Field("name")String name,
            @Field("mobileno") String mobileno,
            @Field("address") String address_,
            @Field("account_no") String accountNo_,
            @Field("ifsc_code")String ifscCode_,
            @Field("sector") String sector,
            @Field("pan_card") String pan_card,
            @Field("adhaar_card") String adhaar_card,
            @Field("pincode")String pincode
             );



    @POST("apis/Payment/response")
    Call<OnTransactionResponseModelClass> getTransactionResponse(@Body TransactionResponseElementsModelClass elementsModelClass);

    @FormUrlEncoded
    @POST("apis/Payment/Walletamount")
    Call<WalletModelClass> getVendorWallet(@Field("vendor_id") String id);


}
