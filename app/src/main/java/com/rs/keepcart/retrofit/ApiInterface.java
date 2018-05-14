package com.rs.keepcart.retrofit;


import com.rs.keepcart.login.LoginModelClass;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sam on 4/10/2018.
 */

public interface ApiInterface {

//    @FormUrlEncoded
//    @POST("user/signup")
//    Call<SignUpModelClass> getRegisterCall(@Field("name") String name_, @Field("email") String email_, @Field("password") String password_, @Field("mobileNo") String mobileNo_, @Field("login_type") String login_type, @Field("social_id") String Social_id);

    @FormUrlEncoded
    @POST("vendor/login")
    Call<LoginModelClass> getLoginCall(@Field("id") String id, @Field("password") String password_
                                       /*@Field("login_type") String login_type, @Field("social_id") String Social_id*/);
}
