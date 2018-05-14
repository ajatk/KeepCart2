package com.rs.keepcart.utills;


import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
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

import java.util.HashMap;

public class GoogleLogIn implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    public MutableLiveData<HashMap<String, String>> googleLiveData;
    private Context context ;

    public GoogleLogIn(Context context) {
        this.context = context;
        googleLiveData = new MutableLiveData<>();
    }

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
            googleSignOut();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("email", email);
            if (email.equals("")) {
                googleLiveData.setValue(null);


            } else {

                googleLiveData.setValue(hashMap);
            }
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



   