package com.rs.keepcart.utills;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class GoogleLoginClass implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Context context;

//    public GoogleLoginClass(LoginInfoClass loginInfoClass, Context context) {
//        this.loginInfoClass = loginInfoClass;
//        this.context = context;
//        googleLiveData = new MutableLiveData<>();
//    }

    public void loginMethod() {
        method();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((Activity) context).startActivityForResult(signInIntent, 8551);
    }

    private void method() {
        if (mGoogleApiClient == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .enableAutoManage((FragmentActivity) context, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }


    public void googleLoginOutput(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            String profileImage = "";
            String id = acct.getId();
            if (acct.getPhotoUrl() != null) {
                profileImage = acct.getPhotoUrl().toString();
            }
            String email = acct.getEmail();

            String firstName = acct.getGivenName();
            String lastName = acct.getFamilyName();
            if (profileImage == null) {
                profileImage = "";
            }
            if (email == null) {
                email = "";
            }
            if (firstName == null) {
                firstName = "";
            }
            if (lastName == null) {
                lastName = "";
            }
//            googleSignOut();
//            if (email.equals("")) {
//                googleLiveData.setValue(Resource.error(ApplicationConstants.HTTP_NO_CONTENT, "Email is not found, Please Sign Up"));
//            } else {
//
//                loginInfoClass.add(email, firstName, lastName, email,
//                        "", id, ApplicationConstants.SOCIAL_LOGIN, "", profileImage);
//                googleLiveData.setValue(Resource.success(null, ""));
//            }
        }

    }

    private void googleSignOut() {
        if (mGoogleApiClient != null) {
            if (mGoogleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
