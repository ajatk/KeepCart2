package com.rs.keepcart.userPart_appUserScreens.userProfile;

import android.app.DatePickerDialog;
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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.paytm.pgsdk.Log;
import com.rs.keepcart.R;
import com.rs.keepcart.databinding.ActivityUserProfileBinding;
import com.rs.keepcart.editProfile.CityAndBankModelClass;
import com.rs.keepcart.editProfile.EditProfile;
import com.rs.keepcart.utills.ApplicationUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    List<String>gender;
    private ActivityUserProfileBinding viewBinding;
    private PopupMenu popupMenuGender, popupMenuJob;
    private  int mYear,mMonth,mDay;
    private String strFromDate;
    private String imageUri = null;
    Uri uri;
    File file;
    String userChoosenTask;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding= DataBindingUtil.setContentView(this,R.layout.activity_user_profile);
        onClickItems();
        popupMenu1();
        popupMenu2();

    }
    public void onClickItems() {
       viewBinding.gender.setOnClickListener(this);
       viewBinding.jobOccupation.setOnClickListener(this);
       viewBinding.tvDateOfBirth.setOnClickListener(this);
       viewBinding.profileChangeImage.setOnClickListener(this);
    }
    public void popupMenu1()
    {  popupMenuGender = new PopupMenu(this,viewBinding.gender);
        popupMenuGender.getMenuInflater().inflate(R.menu.pop_up_spiner, popupMenuGender.getMenu());
        popupMenuGender.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                viewBinding.gender.setText(item.toString());
                //Toast.makeText(getActivity(), "success full click---"+item.getItemId(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void popupMenu2()
    {
        popupMenuJob = new PopupMenu(this,viewBinding.jobOccupation);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.gender:
                popupMenuGender.show();
                break;
            case R.id.job_occupation:
                popupMenuJob.show();
                break;
            case R.id.tv_dateOfBirth:
                birthDate();
                break;
            case R.id.profile_changeImage:
                selectImageResource();
                break;
        }
    }

    private void birthDate() {

        final Calendar cTo = Calendar.getInstance();
        mYear = cTo.get(Calendar.YEAR);
        mMonth = cTo.get(Calendar.MONTH);
        mDay = cTo.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String day = String.valueOf(dayOfMonth);
                        String month = String.valueOf(monthOfYear+1);
                        if(day.length()==1)
                        {
                            day ="0"+day;
                        }
                        if(month.length()==1)
                        {
                            month ="0"+ month;
                        }

                        viewBinding.tvDateOfBirth.setText(day + "/" + month + "/" + year);
                        //  String dateString = dateFormat.format(calendar.getTime());
                        strFromDate = viewBinding.tvDateOfBirth.getText().toString().trim();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
    }

    private void selectImageResource() {
        /*Example = http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample*/
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        //  View view=getLayoutInflater().inflate(R.layout.dailog_title,null);builder.setCustomTitle(view);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = EditProfile.Utility.checkPermission(context);

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
            case EditProfile.Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
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

                    //uploadImage();
                } else if  (requestCode == 11 ) {
                    onCaptureImageResult(data);
                   // uploadImage();
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
        imageUri = ApplicationUtils.getFilePathFromURI(this, data.getData());
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
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
