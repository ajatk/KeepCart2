package com.rs.keepcart.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.rs.keepcart.comingSoon.VendorProccessActivity;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.FragmentSignUpBinding;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.MySharedData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Fragment implements  View.OnClickListener {
    public String TAG = SignUp.class.getSimpleName();
    private Pattern pattern;
    private Matcher matcher;
    private Button signUp2;
    private PopupMenu popupMenuReg, popupMenuJob;
    private Context context;
    private FragmentSignUpBinding viewBinding;
    String registerAs_, name_, email_, password_, mobileNo_, location_, jobOccupation_, mySharedLocation;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    String accountType;

    private String latitude;
    private String longitude;
    private String accountTypez;
    private SignUpViewModelClass viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        viewBinding = DataBindingUtil.bind(view);

        context= getActivity();
        mySharedLocation = MySharedData.getGeneralSaveSession("locate");
        viewBinding.location.setText(mySharedLocation);
        pattern = Pattern.compile(EMAIL_PATTERN);
        viewBinding.signUp1.setOnClickListener(this);
        popupMenu1();
        popupMenu2();
        viewBinding.registerAs.setOnClickListener(this);
        viewBinding.jobOccupation.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(SignUpViewModelClass.class);
//        viewModel.inject();
        viewModel.loginLiveData.observe(this,this::signUpResponse);
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
               /* Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
                startActivity(intent);*/

                popupMenuReg.show();
                break;
            case R.id.job_occupation:
                InputMethodManager imm2 = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(viewBinding.jobOccupation.getWindowToken(),0);
                popupMenuJob.show();
                break;

              case R.id.location:

                  InputMethodManager imm3 = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                  imm3.hideSoftInputFromWindow(viewBinding.jobOccupation.getWindowToken(),0);

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

                 //Toast.makeText(getActivity(), "success full click---"+item.getItemId(), Toast.LENGTH_SHORT).show();

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

          accountTypez = viewBinding.registerAs.getText().toString();
         Log.i(TAG, "vender details ........." + accountTypez);
         if(accountTypez.equals("Vendor")){
              accountType = String.valueOf("2");
             Log.i(TAG, "vender details3 ........." + accountTypez);
             Log.i(TAG, "vender details31 ........." + accountType);

         }else if(accountTypez.equals("User")){
              accountType = String.valueOf("3");
             Log.i(TAG, "vender details 4........." + accountTypez);
             Log.i(TAG, "vender details 41........." + accountType);


         }

         registerAs_ = viewBinding.registerAs.getText().toString();
         name_ = viewBinding.name.getText().toString();
         email_ = viewBinding.email.getText().toString().trim();
         password_ = viewBinding.password.getText().toString();
         mobileNo_ = viewBinding.mobileno.getText().toString();
         location_ = viewBinding.location.getText().toString().trim();
         jobOccupation_ = viewBinding.jobOccupation.getText().toString();
         if (name_.equals("") && email_.equals("") && password_.equals("") && mobileNo_.equals("")&& accountTypez.equals("") || location_.equals("") && jobOccupation_.equals("")) {
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
            /* Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);
             startActivity(intent);*/
                viewModel.loginApii(accountType,name_, email_,password_,mobileNo_,latitude,longitude,jobOccupation_);

         }
     }
    private void signUpResponse(Resource<SignUpModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);
        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();
                if (status == 200) {

                  Toast.makeText(getActivity(), resource.data.getMessage(), Toast.LENGTH_SHORT).show();
                    if(registerAs_.equals(accountTypez))
                    {
                        Intent intent = new Intent(getContext(), VendorProccessActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK & Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                        getActivity().finish();
                    } else
                    {
                        Toast.makeText(getActivity(), resource.data.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Snackbar.make(viewBinding.getRoot(), resource.data.getMessage(), Snackbar.LENGTH_SHORT).show();
                }

                break;
            case ApplicationConstants.SHOW_ONLY_MSG:
                Snackbar.make(viewBinding.getRoot(), resource.message, Snackbar.LENGTH_SHORT).show();
                break;

        }
    }
    public boolean validate(final String hex) {
         matcher = pattern.matcher(hex);
         return matcher.matches();
     }
}
