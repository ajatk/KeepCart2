package com.rs.keepcart.editProfile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.paytm.pgsdk.Log;
import com.rs.keepcart.R;
import com.rs.keepcart.afterLogin.selectMagazines.SelectMagazineActivity;
import com.rs.keepcart.afterLogin.selectNewspaper.SelectNewsPaperActivity;
import com.rs.keepcart.dashboard.HomeActivity;
import com.rs.keepcart.databinding.FragmentEditProfileBinding;
import com.rs.keepcart.editProfile.profileModel.EditProfileImageModelClass;
import com.rs.keepcart.editProfile.profileModel.EditProfileModelClass;
import com.rs.keepcart.editProfile.profileModel.GetDataProfileModelClass;
import com.rs.keepcart.retrofit.ApiClient;
import com.rs.keepcart.retrofit.ApiInterface;
import com.rs.keepcart.retrofit.Resource;
import com.rs.keepcart.utills.ApplicationConstants;
import com.rs.keepcart.utills.ApplicationUtils;
import com.rs.keepcart.utills.MySharedData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


public class EditProfile extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String Base_URL = "http://www.uicreations.com/keepkart/assets/images/";
    public static final int RequestPermissionCode = 7;
    public static final String TAG = EditProfile.class.getSimpleName();
    private static final String imageUploadUrl = "http://www.uicreations.com/keepkart/apis/Vendor/Image";
    private static final int INTENT_REQUEST_CODE = 100;
    Intent CamIntent, GalIntent, CropIntent;
    Uri uri;
    File file;
    String userChoosenTask;
    EditText email_tv, name_tv, mobileNo_tv, address_tv, accountNo_tv,
            ifscCode_tv, sector_tv, panCard_tv, adharCard_tv, pinCode_tv, bankkName_tv, cityName_tv;
    TextView cityNames;
    private FragmentEditProfileBinding viewBinding;
    private TextView titleSet;
    private Context context;
    private Toolbar toolbarr;
    private NavigationView navView;
    private Fragment fragment;
    private EditprofileViewModelClass viewModelClass;
    private InputStream is;
    private ArrayList<String> bankList;
    private ArrayList<CityAndBankModelClass> cityList;
    private CityAndBankModelClass bankModelClass;
    private String imageUri = null;
    private String email_, name_, mobileNo_, address_, accountNo_, ifscCode_, sector_, panCard,
            adharCard, pinCode_, bankkName, cityName, bankN, cityN, imageNameSaved, image;
    private String email_S, name_S, mobileNo_S, address_S, accountNo_S,
            ifscCode_S, sector_S, panCard_S, adharCard_S, pinCode_S, bankkName_S, cityName_S;
    private Spinner bankname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        viewBinding = DataBindingUtil.bind(view);
        context = getActivity();
        titleSet = ((HomeActivity) getActivity()).findViewById(R.id.page_title);
        titleSet.setText("Profile");
        navView = ((HomeActivity) getActivity()).findViewById(R.id.nav_view);
        setProfilData();
        image = MySharedData.getGeneralSaveSession("image_saved");

        Glide.with(context).load(Base_URL + image)
                .error(R.drawable.banner)
                .into(viewBinding.profileImageView);
        clickSetListner();
        if (CheckingPermissionIsEnabledOrNot()) {
            Toast.makeText(context, "Permissions already given ", Toast.LENGTH_SHORT).show();
        } else {
            RequestMultiplePermission();
        }

        cityAndBankList();
        //signUpMethod();

        return view;
    }

    public void cityAndBankList() {
        cityList = new ArrayList<>();
        bankList = new ArrayList<>();
        String[] listbankk = {"Select Bank", "Allahabad Bank", "Andhra Bank", "Bank of Baroda", "Bank of India", "Bank of Maharashtra",
                "Canara Bank", "Central Bank of India", "Corporation Bank", "Dena Bank", "Indian Bank", "Indian Overseas Bank",
                "IDBI Bank",
                "Oriental Bank of Commerce", "Punjab and Sind Bank", "Punjab National Bank", "State Bank of India",
                "Syndicate Bank", "UCO Bank", "Union Bank of India", "United Bank of India", "Axis Bank", "City Union Bank",
                "DCB Bank", "Dhanlaxmi Bank", "Federal Bank", "HDFC Bank", "ICICI Bank", "IndusInd Bank", "IDFC Bank", "Karnataka Bank", "Karur Vysya Bank",
                "Kotak Mahindra Bank", "Lakshmi Vilas Bank", "Nainital Bank", "RBL Bank", "South Indian Bank", "Yes Bank"};
        List<String> newList = Arrays.asList(listbankk);

        cityList.add(new CityAndBankModelClass("Select city"));
        cityList.add(new CityAndBankModelClass("Chandigarh"));
        cityList.add(new CityAndBankModelClass("Mohali"));
        cityList.add(new CityAndBankModelClass("PunchKula"));

        bankList.addAll(newList);

        ArrayAdapter<String> bankAdapter = new ArrayAdapter<>(context, R.layout.spinner_textview, bankList);
        bankAdapter.setDropDownViewResource(R.layout.spinner_textview);
        viewBinding.bankNameSpinner.setAdapter(bankAdapter);

        ArrayAdapter<CityAndBankModelClass> cityAdapter = new ArrayAdapter<>(context, R.layout.spinner_textview, cityList);
        cityAdapter.setDropDownViewResource(R.layout.spinner_textview);
        viewBinding.citySpinner.setAdapter(cityAdapter);
    }

    public void clickSetListner() {

        viewBinding.editProfileText.setOnClickListener(this);
        viewBinding.profileChangeImage.setOnClickListener(this);
        viewBinding.selectNewspaper.setOnClickListener(this);
        viewBinding.selectMagazines.setOnClickListener(this);
        viewBinding.buttonSubmit.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner = (Spinner) adapterView;
        if (spinner.getId() == R.id.bankName_spinner) {
            //casetype = adapterView.getItemAtPosition(i).toString();
            for (String cityModelClass : bankList) {
                bankkName = bankList.get(i);
            }
        }
        if (spinner.getId() == R.id.city_spinner) {
            //casetype = adapterView.getItemAtPosition(i).toString();
            for (CityAndBankModelClass cityModelClass : cityList) {
                if (cityModelClass.getCity().equals(adapterView.getItemAtPosition(i).toString())) {
                    cityName = cityModelClass.getCity();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void profileDetails() {

        email_ = MySharedData.getGeneralSaveSession("email_dash");
        accountNo_ = viewBinding.editAcountNo.getText().toString();
        name_ = viewBinding.editName.getText().toString();
        address_ = viewBinding.editAddress.getText().toString();
        mobileNo_ = viewBinding.editMobileNo.getText().toString();
        ifscCode_ = viewBinding.editTextIfscCode.getText().toString().trim();
        sector_ = viewBinding.sectorAddress.getText().toString();
        adharCard = viewBinding.adharCard.getText().toString();
        pinCode_ = viewBinding.editTextPincode.getText().toString();
        panCard = viewBinding.panCard.getText().toString();
        bankN = viewBinding.bankNameSpinner.getSelectedItem().toString();
        cityN = viewBinding.citySpinner.getSelectedItem().toString();

        try {


            if (name_.equals("") && email_.equals("") && accountNo_.equals("") && mobileNo_.equals("") && address_.equals("") && ifscCode_.equals("")
                    && sector_.equals("")
                    && adharCard.equals("") && pinCode_.equals("") && panCard.equals("") && bankN.equals("") && cityN.equals("")) {
                Toast.makeText(getActivity(), "All Field Require", Toast.LENGTH_SHORT).show();

            } else if (name_.equals("")) {
                viewBinding.editName.setError("Enter Your Name");
                viewBinding.editName.requestFocus();

            } else if (accountNo_.equals("")) {
                viewBinding.editAcountNo.setError("Enter Your AccountNo");
                viewBinding.editAcountNo.requestFocus();
            } else if (address_.equals("")) {
                viewBinding.editAddress.setError("Please Your Address");
                viewBinding.editAddress.requestFocus();
            } else if (mobileNo_.equals("") && mobileNo_.length() < 8 && mobileNo_.length() > 10) {
                viewBinding.editMobileNo.setError("Please valid mobileNo");
                viewBinding.editMobileNo.requestFocus();
            } else if (ifscCode_.equals("")) {
                viewBinding.editTextIfscCode.setError("Registered Bank Ifsce Code");
                viewBinding.editTextIfscCode.requestFocus();
            } else if (sector_.equals("")) {
                viewBinding.sectorAddress.setError("Enter Sector ");
                viewBinding.sectorAddress.requestFocus();
            } else if (adharCard.equals("")) {
                viewBinding.adharCard.setError("Enter Your Adhaar Number");
                viewBinding.adharCard.requestFocus();
            } else if (pinCode_.equals("")) {
                viewBinding.editTextPincode.setError("Enter Your PinCode");
                viewBinding.editTextPincode.requestFocus();
            } else if (panCard.equals("")) {
                viewBinding.panCard.setError("Enter Your PanCard");
                viewBinding.panCard.requestFocus();
            } else if (viewBinding.bankNameSpinner.getSelectedItem().equals("")) {
                viewBinding.bank.setError("Enter Your Bank Name");
                viewBinding.bank.requestFocus();
            } else if (viewBinding.citySpinner.getSelectedItem().equals("")) {
                viewBinding.city.setError(" Enter Your City");
                viewBinding.city.requestFocus();
            } else if (!name_.equals("")
                    && !email_.equals("")
                    && !accountNo_.equals("")
                    && !mobileNo_.equals("")
                    && !address_.equals("") &&
                    !ifscCode_.equals("")
                    && !sector_.equals("")
                    && !adharCard.equals("")
                    && !pinCode_.equals("")
                    && !panCard.equals("")
                    && !viewBinding.bankNameSpinner.getSelectedItem().equals("")
                    && !viewBinding.citySpinner.getSelectedItem().equals("")) {


                viewModelClass.loginApiEditProfile(new CityAndBankModelClass(cityN, bankN, email_,
                        name_,
                        mobileNo_,
                        address_, accountNo_, ifscCode_,
                        sector_, panCard, adharCard, pinCode_));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signUpResponse(Resource<EditProfileModelClass> resource) {
        viewBinding.progressBarLog.setVisibility(View.GONE);


        switch (resource.status) {
            case ApplicationConstants.LOADING:
                viewBinding.progressBarLog.setVisibility(View.VISIBLE);
                break;
            case ApplicationConstants.HTTP_SUCCESS:
                int status = resource.data.getStatus();
                if (status == 200) {

                    try {

                        Snackbar.make(viewBinding.getRoot(), resource.data.getMessage(), Snackbar.LENGTH_SHORT).show();
                    } catch (Exception e) {

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
private int count =0;
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profileText:
                count++;
                if(count==1)
                {
                    count++;
                    viewBinding.editProfileText.setBackgroundColor(R.color.gray_set);
                    viewBinding.city.setText("");
                    viewBinding.bank.setText("");
                    viewBinding.citySpinner.setVisibility(View.VISIBLE);
                    viewBinding.bankNameSpinner.setVisibility(View.VISIBLE);
                    viewModelClass = ViewModelProviders.of(this).get(EditprofileViewModelClass.class);
                    viewModelClass.loginLiveData.observe(this, this::signUpResponse);
                } else if(count>1){
                    viewBinding.editProfileText.setBackgroundColor(Color.TRANSPARENT);
                    count=0;

                }

//                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment();
//                bottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
                break;
            case R.id.profile_changeImage:
                selectImageResource();
                break;
            case R.id.selectNewspaper:
                Intent intentN = new Intent(context, SelectNewsPaperActivity.class);
                context.startActivity(intentN);
                break;

            case R.id.selectMagazines:
                Intent intentM = new Intent(context, SelectMagazineActivity.class);
                context.startActivity(intentM);
                break;

            case R.id.button_submit:
                profileDetails();
                break;
        }
    }



    private void selectImageResource() {
        /*Example = http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample*/
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        //  View view=getLayoutInflater().inflate(R.layout.dailog_title,null);builder.setCustomTitle(view);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermission(context);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";

                    if (result)
                        cameraIntent();
                    dialog.dismiss();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                    dialog.dismiss();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 11);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), 12);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadStoragePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (CameraPermission && ReadStoragePermission) {
                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && data != null) {
                if (requestCode == 12) {
                    onSelectFromGalleryResult(data);

                    uploadImage();
                } else if  (requestCode == 11 ) {
                    onCaptureImageResult(data);
                    uploadImage();
                }
            }

        } catch (Exception e) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        uri = data.getData();
       // uri = CropImage.getPickImageResultUri(context,data);
        imageUri = getRealPathFromURI(uri);

        imageUri = Objects.requireNonNull(uri).toString();
        Log.e("IMage uri" + uri, "xjdkgh");
        imageUri = ApplicationUtils.getFilePathFromURI(getActivity(), data.getData());
        if (imageUri == null) {
            imageUri = uri.getPath();
        }
        viewBinding.profileImageView.setImageURI(uri);
           // viewBinding.profileImageView.setImageBitmap(bm);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onCaptureImageResult(Intent data) {
        Bitmap vendorimage = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
       // user_image.setImageBitmap(vendorimage);
        Log.e("IMage uri" + vendorimage, "x,jdkgh");
        //uri = CropImage.getPickImageResultUri(context,data);
        imageUri = getRealPathFromURI(getImageUri(context, vendorimage));
        Log.e("IMage uri" + vendorimage, "x,jdkgh");
        uri = getImageUri(context, vendorimage);

        viewBinding.profileImageView.setImageBitmap(vendorimage);

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



    //Permission function starts from here
    private void RequestMultiplePermission() {
        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions((Activity) context, new String[]
                {
                        CAMERA,
                        READ_EXTERNAL_STORAGE
                }, RequestPermissionCode);
    }

    // Checking permission is enabled or not using function starts from here.
    public boolean CheckingPermissionIsEnabledOrNot() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED;
    }


    public void uploadImage() {

        try {
            String id_ = MySharedData.getGeneralSaveSession("userId");
            ApiInterface retrofitInterface = ApiClient.getClient().create(ApiInterface.class);

            File file = new File(imageUri);
            imageNameSaved = file.getName();
           RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

//            CreateUserApi createUserApi = new CreateUserApi(imageUri, "http://www.uicreations.com/keepkart/apis/Vendor/Image", "profile_Image", id_, context);
//            createUserApi.execute();

           MultipartBody.Part body = MultipartBody.Part.createFormData("profile_Image", file.getName(), requestFile);
            RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), id_);

            Call<EditProfileImageModelClass> call = retrofitInterface.getUploadImage(id, body);

           call.enqueue(new Callback<EditProfileImageModelClass>() {
                @Override
                public void onResponse(Call<EditProfileImageModelClass> call, retrofit2.Response<EditProfileImageModelClass> response) {

                    // mProgressBar.setVisibility(View.GONE);
                    EditProfileImageModelClass responseBody = response.body();
                    if (response.isSuccessful() ) {

                        if(response.body().getStatus()!=null)
                        {
                            if (response.body().getStatus() == 200) {
                              MySharedData.setGeneralSaveSession("imageNameSaved",imageNameSaved);

                                Toast.makeText(context, "response 200", Toast.LENGTH_SHORT).show();
                                // Snackbar.make(findViewById(R.id.content), responseBody.getMessage(),Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "response Status is not 200", Toast.LENGTH_SHORT).show();
                        }
                        }

                }

                @Override
                public void onFailure(Call<EditProfileImageModelClass> call, Throwable t) {


                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onFailure: " + e.getMessage());
        }
    }

    public void setProfilData() {
        String id_ = MySharedData.getGeneralSaveSession("userId");
        ApiInterface retrofitInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetDataProfileModelClass> call = retrofitInterface.getProfileData(id_);
        // viewBinding.progressBarLog.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<GetDataProfileModelClass>() {
            @Override
            public void onResponse(Call<GetDataProfileModelClass> call, retrofit2.Response<GetDataProfileModelClass> response) {

                // mProgressBar.setVisibility(View.GONE);
                GetDataProfileModelClass responseBody = response.body();

                if (response.isSuccessful()) {

                    if (response.body().getStatus() != null) {
                        if (response.body().getStatus() == 200) {
                            //viewBinding.editName.ed
                            viewBinding.citySpinner.setVisibility(View.GONE);
                            viewBinding.bankNameSpinner.setVisibility(View.GONE);
                            name_S = MySharedData.getGeneralSaveSession("name_p");
                            viewBinding.editName.setText(responseBody.getProfile().getName());
                            viewBinding.editAcountNo.setText(responseBody.getProfile().getAccountNo());
                            viewBinding.editAddress.setText(responseBody.getProfile().getAddress());
                            viewBinding.editMobileNo.setText(responseBody.getProfile().getMobile());
                            viewBinding.editTextIfscCode.setText(responseBody.getProfile().getIfscCode());
                            viewBinding.sectorAddress.setText(responseBody.getProfile().getSector());
                            viewBinding.adharCard.setText(responseBody.getProfile().getAdhaarCard());
                            viewBinding.editTextPincode.setText((CharSequence) responseBody.getProfile().getPincode());
                            viewBinding.panCard.setText(responseBody.getProfile().getPanCard());
                            //viewBinding.citySpinner.getSelectedItem();
                            viewBinding.city.setText(responseBody.getProfile().getCity());
//                            viewBinding.citySpinner.setPrompt(responseBody.getProfile().getCity());
//                            viewBinding.bankNameSpinner.setPrompt(responseBody.getProfile().getCity());
                             viewBinding.bank.setText(responseBody.getProfile().getBankName());

                            // Toast.makeText(context, "response 200", Toast.LENGTH_SHORT).show();
                            //Snackbar.make(titleSet, responseBody.getProfile().getm,Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "response Status is not 200", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetDataProfileModelClass> call, Throwable t) {


                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context) {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Glide.with(context).load(Base_URL + image)
                .error(R.drawable.banner)
                .into(viewBinding.profileImageView);

    }

}
