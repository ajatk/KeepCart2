package com.rs.keepcart.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.rs.keepcart.HomePage;
import com.rs.keepcart.LoginActivity;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentSignUpBinding;
import com.rs.keepcart.model.RegisteredModel;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.utills.MySharedData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends Fragment implements  View.OnClickListener {
    private Pattern pattern;
    private Matcher matcher;
    private Button signUp2;
    private PopupMenu popupMenuReg, popupMenuJob;
    private Context context;
    private FragmentSignUpBinding viewBinding;
    String registerAs_, name_, email_, password_, mobileNo_, location_, jobOccupation_;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context= getActivity();
        pattern = Pattern.compile(EMAIL_PATTERN);
        viewBinding.signUp1.setOnClickListener(this);
        popupMenu1();
        popupMenu2();
        viewBinding.registerAs.setOnClickListener(this);
        viewBinding.jobOccupation.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signUp1:
                signUpMethod();

            break;

            case R.id.register_as:
                InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewBinding.registerAs.getWindowToken(),0);
                Intent intent = new Intent(context.getApplicationContext(), HomePage.class);
                startActivity(intent);

               // popupMenuReg.show();
                break;
            case R.id.job_occupation:
                InputMethodManager imm2 = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(viewBinding.jobOccupation.getWindowToken(),0);
                popupMenuJob.show();
                break;
        }
    }
     public void popupMenu1()
     {  popupMenuReg = new PopupMenu(getActivity(),viewBinding.registerAs);
         popupMenuReg.getMenuInflater().inflate(R.menu.pop_up_spiner, popupMenuReg.getMenu());
         popupMenuReg.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 viewBinding.registerAs.setText(item.toString());
                 // Toast.makeText(getActivity(), "success full click------2", Toast.LENGTH_SHORT).show();

                 return true;
             }
         });
     }

    public void popupMenu2()
    {
        popupMenuJob = new PopupMenu(getActivity(),viewBinding.jobOccupation);
        popupMenuJob.getMenuInflater().inflate(R.menu.pop_up_spiner_job, popupMenuJob.getMenu());
        popupMenuJob.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                viewBinding.jobOccupation.setText(item.toString());
                // Toast.makeText(getActivity(), "success full click------2", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
     private void signUpMethod()
     {
         registerAs_ = viewBinding.registerAs.getText().toString();
         name_ = viewBinding.name.getText().toString();
         email_ = viewBinding.email.getText().toString().trim();
         password_ = viewBinding.password.getText().toString();
         mobileNo_ = viewBinding.mobileno.getText().toString();
         location_ = viewBinding.location.getText().toString();
         jobOccupation_ = viewBinding.jobOccupation.getText().toString();
         if (name_.equals("") && email_.equals("") && password_.equals("") && mobileNo_.equals("")&& registerAs_.equals("") && location_.equals("") && jobOccupation_.equals("")) {
             Toast.makeText(getActivity(), "All Field Require", Toast.LENGTH_SHORT).show();

         } else if (name_.equals("")) {
             viewBinding.name.setError("Enter Your Name");
             viewBinding.name.requestFocus();

         } else if (!validate(email_)) {
             viewBinding.email.setError("Enter valid Email");
             viewBinding.email.requestFocus();
         } else if (password_.equals("") || password_.length() < 8) {
             viewBinding.password.setError("Please atleast 8 character");
             viewBinding.password.requestFocus();
         } else if (mobileNo_.equals("") && mobileNo_.length() < 8 &&  mobileNo_.length()>10) {
             viewBinding.password.setError("Please valid mobileNo");
             viewBinding.password.requestFocus();
         } else if(registerAs_.equals(""))
         {
             viewBinding.registerAs.setError(" register your self as");
             viewBinding.registerAs.requestFocus();
         }else if(location_.equals(""))
         {
             viewBinding.location.setError(" register your self as");
             viewBinding.location.requestFocus();
         }else if(jobOccupation_.equals(""))
         {
             viewBinding.jobOccupation.setError(" register your self as");
             viewBinding.jobOccupation.requestFocus();
         }

         else if
                 (!name_.equals("") && !email_.equals("") && !password_.equals("") && !mobileNo_.equals("") && !registerAs_.equals("") && !location_.equals("") && !jobOccupation_.equals(""))
         {
             Intent intent = new Intent(context.getApplicationContext(), HomePage.class);
             startActivity(intent);

             /*ApiInterface apiInterface;
             apiInterface = ApiClient.getClient().create(ApiInterface.class);
             Call<RegisteredModel> call = apiInterface.getRegisterCall(name_, email_, password_, mobileNo_);
             call.enqueue(new Callback<RegisteredModel>() {
                 @Override
                 public void onResponse(Call<RegisteredModel> call, Response<RegisteredModel> response) {
                     if (response.isSuccessful()) {
                         String status = response.body().getMessage();
                         int st = response.body().getStatus();
                         if(st==200) {
                             MySharedData.setGeneralSaveSession("Email", email_);
                             MySharedData.setGeneralSaveSession("password", password_);
                             MySharedData.setGeneralSaveSession("mobileNo", mobileNo_);
                             Toast.makeText(context.getApplicationContext(), "Status : Registered Successfully", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(context.getApplicationContext(), HomePage.class);
                             startActivity(intent);
                         }else
                         {
                             Toast.makeText(context.getApplicationContext(), "Registeration Error ", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }

                 @Override
                 public void onFailure(Call<RegisteredModel> call, Throwable t) {
                     Toast.makeText(context.getApplicationContext(), "Status not Found", Toast.LENGTH_SHORT).show();
                 }
             });*/
         }
     }
    public boolean validate(final String hex) {
         matcher = pattern.matcher(hex);
         return matcher.matches();
     }
}
