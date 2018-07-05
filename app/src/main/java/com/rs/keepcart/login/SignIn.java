package com.rs.keepcart.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.rs.keepcart.R;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectNewsPaperActivity;
import com.rs.keepcart.comingSoon.VendorProccessActivity;
import com.rs.keepcart.dashboard.HomeActivity;
import com.rs.keepcart.databinding.FragmentSignInBinding;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.session.SessionManager;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MyApplication;
import com.rs.keepcart.utills.MySharedData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends Fragment implements View.OnClickListener {

    LoginViewModel viewModel;
    SessionManager session;
    private Context context;
    private ProgressBar bar;
    private FragmentSignInBinding viewBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        context = getActivity();
        viewBinding = DataBindingUtil.bind(view);
        viewBinding.login.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.loginLiveData.observe(this, this::loginResponse);
        String newsPerSelected = MySharedData.getGeneralSaveSession("newsPerSelected");
        session = new SessionManager(getActivity());
        if (session.isLoggedIn()) {
            if (newsPerSelected != null) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getActivity().finishAffinity();
                }
            } else {
                Intent intentNews = new Intent(getActivity(), SelectNewsPaperActivity.class);
                startActivity(intentNews);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getActivity().finishAffinity();
                }
            }
        }
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                if (((MyApplication) getActivity().getApplication()).isOnline()) {
                    addLoginMethod();
                } else {
                    Snackbar.make(getView(), "Internet is not Available", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void addLoginMethod() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewBinding.getRoot().getWindowToken(), 0);

        String email = viewBinding.email.getText().toString();
        String password_ = viewBinding.password.getText().toString();
        if (email.isEmpty() && !validate(email)) {
            viewBinding.email.setError("please fill valid email");
            viewBinding.email.requestFocus();
        } else if (password_.isEmpty()) {
            viewBinding.password.setError("Not a valid adress");
            viewBinding.password.requestFocus();
        } else {
            viewBinding.progressBarLog.setVisibility(View.VISIBLE);
            LoginInfoModel loginInfoModel = new LoginInfoModel(
                    email, password_);
            viewModel.loginApii(loginInfoModel);
        }
    }

    private void loginResponse(Resource<LoginModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:

                int status = resource.data.getStatus();
                String stats = MySharedData.getGeneralSaveSession("status0");
                String activ_stats = MySharedData.getGeneralSaveSession("Act_status_1");


                if (status==200) {
                    if (activ_stats.equals("1") && !MySharedData.getGeneralSaveSession("userId").equals("") &&
                            !MySharedData.getGeneralSaveSession("pass").equals("")) {
                        Intent intent = new Intent(context, HomeActivity.class);
                        startActivity(intent);
                    } else if (activ_stats.equals("0")) {
                        Intent inV0 = new Intent(context, VendorProccessActivity.class);
                        startActivity(inV0);
                        // finish();
                    } else if (activ_stats.equals("1")) {
                        session.setLogin(true);
                        Intent inV0 = new Intent(context, SelectNewsPaperActivity.class);
                        startActivity(inV0);
                    } else {
                        Intent intent = new Intent(context, HomeActivity.class);
                        startActivity(intent);
                    }
                }

                Toast.makeText(context, resource.data.getMessage(), Toast.LENGTH_SHORT).show();
               break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean validate(final String hex) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public void setFocusableInput(View view) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewBinding.email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                focusMethod(v, hasFocus);
            }
        });
        viewBinding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                focusMethod(v, hasFocus);
            }
        });
    }

    public void focusMethod(View v, boolean hasFocus) {
        if (hasFocus) {
            v.setBackgroundResource(R.drawable.edit_on_tab_selected_);
        } else {
            v.setBackgroundResource(R.drawable.edit_none_tab);
          //  v.setBackgroundResource(R.id);
            // v.setBackgroundDrawable(Drawable.createFromPath(String.valueOf(R.drawable.login_underline)));

        }
    }
    /* public void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }*/

}
