package com.htl.jsf.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.htl.jsf.Adapters.CityListAdatper;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiClient;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.CityDataResponse;
import com.htl.jsf.Models.CityModel;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityRegisterBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SuccessDialog;
import com.htl.jsf.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    String image_base64 = "";
    ArrayList<String> listCode = new ArrayList<String>();
    ArrayList<String> listSpinner = new ArrayList<String>();

    //Bitmap to get image from gallery
    private Bitmap bitmap;
    private ActivityRegisterBinding registerBinding;
    private String select__city;
    private ArrayAdapter<String> adapter;
    private List<CityDataResponse> leaveTypeItems;
    private String TAG = "RegisterActivity=>";
    private String selectedCityCode;
    private List<CityDataResponse> citylist;
    public String city_name, city_id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }


    private void initView() {
//        img_back_btn = findViewById(R.id.img_back_btn);
//        edit_image = findViewById(R.id.edit_image);
//        edit_fullname = findViewById(R.id.edit_fullname);
//        edit_mobilenumber = findViewById(R.id.edit_mobilenumber);
//        spinner = findViewById(R.id.spinner);
//        get_otp = findViewById(R.id.get_otp);
        registerBinding.imgBackBtn.setOnClickListener(this);
        registerBinding.ivAddImage.setOnClickListener(this);
        registerBinding.getOtp.setOnClickListener(this);
//        registerBinding.spinner.setOnItemSelectedListener(this);
        registerBinding.tvSelectcity.setOnClickListener(this);

//        listSpinner.add(0, getResources().getString(R.string.select_your_city));
        citylist = new ArrayList<>();
//        adapter = new ArrayAdapter<>(RegisterActivity.this, R.layout.spinne_item, listSpinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        registerBinding.spinner.setAdapter(adapter);
        if (Utils.isOnline(RegisterActivity.this)) {
//            initSpinnerType();
            callCityApi();
        } else {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.check_internet_connection));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_otp:
                Utils.hideKeyboard(RegisterActivity.this);
                validationBefore_submit();
                break;

            case R.id.img_back_btn:
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                onBackPressed();
                finish();
                break;

            case R.id.iv_add_image:
                selectImage();

                break;
            case R.id.tv_selectcity:
                showChangeDialog(RegisterActivity.this);
                break;
        }
    }








    private void showChangeDialog(final RegisterActivity eventActivity) {
        final Dialog dialog = new Dialog(RegisterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_citylist_layout);
//        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        RecyclerView rv_city_list = dialog.findViewById(R.id.rv_city_list);

        rv_city_list.setLayoutManager(new LinearLayoutManager(RegisterActivity.this));
        rv_city_list.setAdapter(new CityListAdatper(this, citylist, new CityListAdatper.onCityItemClick() {
            @Override
            public void cityItem(int posi, String city_id, String city_name) {
                eventActivity.city_id = city_id;
                eventActivity.city_name = city_name;
                Utils.loge(1, TAG, "" + "pos" + posi + "\ncityid" + city_id + "\ncityname" + city_name);
//                updateMenuTitles(city_name);
                registerBinding.tvSelectcity.setText(city_name);
                dialog.dismiss();
            }
        }));


    }


    private void validationBefore_submit() {
        // validate
        String fullname = registerBinding.editFullname.getText().toString().trim();
        String mobilenumber = registerBinding.editMobilenumber.getText().toString().trim();
        String selectcity = registerBinding.tvSelectcity.getText().toString().trim();

        Utils.loge(1, TAG, "mobileno" + mobilenumber.length());
        if (TextUtils.isEmpty(fullname)) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.enter_name_error));
        } else if (TextUtils.isEmpty(mobilenumber)) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.enter_mobile_error));
        } else if (mobilenumber.length() < 10) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.mobiel_length_error));
        } else if (selectcity.isEmpty()) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.select_your_city));
        } else if (TextUtils.isEmpty(image_base64)) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.please_select_image));
        } else {
//            registerBinding.getOtp.setEnabled(false);
            callApiGetOptApi(fullname, mobilenumber, select__city, image_base64);
        }


    }

    private void callApiGetOptApi(String fullname, String mobilenumber, String select__city, final String image_base64) {

        if (TextUtils.isEmpty(city_id)) {
            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.valid_city_error));
            return;
        }
        Utils.loge(1, TAG, "name" + fullname + "\nMoblie" + mobilenumber + "\nselectCity" + select__city);
        ProgressDialog.showProgressDio(RegisterActivity.this);
        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().NAME, fullname);
        jsonObject.addProperty(ApiClass.getmApiClass().MOBILE, mobilenumber);
        jsonObject.addProperty(ApiClass.getmApiClass().CITY_ID, city_id);
        jsonObject.addProperty(ApiClass.getmApiClass().IMAGE, image_base64);
        Call<JsonObject> call = mApiInterface.addGetOtpfromRow(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                registerBinding.getOtp.setEnabled(true);
                try {

                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonObject" + jsonObject);
                    if (response.code() == 200) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
//                            showDialog(jsonObject.getString("message"), RegisterActivity.this);
                            if (jsonObject.getString("message").equalsIgnoreCase("Please Enter Otp")) {
                                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                                intent.putExtra(ApiClass.getmApiClass().COMINGFROM, "RegisterActivity");
                                intent.putExtra(ApiClass.getmApiClass().NAME, registerBinding.editFullname.getText().toString());
//                                intent.putExtra(ApiClass.getmApiClass().IMAGE, image_base64);
                                intent.putExtra(ApiClass.getmApiClass().CITY_ID, selectedCityCode);
                                intent.putExtra(ApiClass.getmApiClass().MOBILE, registerBinding.editMobilenumber.getText().toString().trim());
                                startActivity(intent);
                            }

                        } else if (jsonObject.getString("status").equalsIgnoreCase("unsuccess")) {
//                            Utils.showSnack(registerBinding.getOtp, jsonObject.getString("message"));
                            SuccessDialog.showDialog(jsonObject.getString("message"), RegisterActivity.this);
                        }
                    } else if (response.code() == 400) {
                        Utils.showSnack(registerBinding.getOtp, "Enter complete user information to save");
                    } else if (response.code() == 409) {
                        Utils.showSnack(registerBinding.getOtp, "Already exist Or already performed");
                    } else if (response.code() == 401) {
                        Utils.showSnack(registerBinding.getOtp, "Wrong Otp Or credentials");
                    } else if (response.code() == 404) {
                        Utils.showSnack(registerBinding.getOtp, "Verfication failed");
                    } else {
                        Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.server_error));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                registerBinding.getOtp.setEnabled(true);
                Utils.log(1, TAG, "onFailure" + t.getLocalizedMessage());
                Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.server_error));

            }
        });


    }


    private void selectImage() {
        CropImage.startPickImageActivity(this);

    }

    private void croprequest(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //RESULT FROM SELECTED IMAGE
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            croprequest(imageUri);
        }
        //RESULT FROM CROPING ACTIVITY
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {//result.getUri()
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                    registerBinding.editImage.setImageBitmap(bitmap);
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                    image_profileByte = byteArrayOutputStream.toByteArray();
                    registerBinding.editImage.setVisibility(View.VISIBLE);
                    image_base64 = encodeImage(bitmap);
                    //imageView.setEnabled(true);
                    // save.setEnabled(true);

                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }

    private void initSpinnerType() {
        ProgressDialog.showProgressDio(RegisterActivity.this);
        Call<CityModel> call = ApiClient.getClient().get_citydata();
        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                ProgressDialog.hideProgressDio();
                if (response.isSuccessful()) {
                    leaveTypeItems = response.body().getData();
                    for (int i = 0; i < leaveTypeItems.size(); i++) {
                        String Code = leaveTypeItems.get(i).getCity_id(); // I want to show this when Selected
                        String leaveType = leaveTypeItems.get(i).getCity();
                        listSpinner.add(leaveType);
                        // Add your code into an separate ArrayList
                        listCode.add(Code);
                    }
//                    listSpinner.add(0, "Select Your City");
//

//

//                     adapter = new ArrayAdapter<>(RegisterActivity.this,
//                            R.layout.spinne_item, listSpinner);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinner.setAdapter(adapter);
                } else {

                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {

                call.cancel();
                Log.d("errorresult", t.getMessage() + "");

            }
        });


    }


//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//        if (adapterView.getItemAtPosition(position).equals("Select Your City")) ;
//
//        select__city = adapterView.getItemAtPosition(position).toString();
//        if (select__city.equalsIgnoreCase(getResources().getString(R.string.select_your_city))) {
//        } else {
//            selectedCityCode = listCode.get(position);
//        }
////        Toast.makeText(RegisterActivity.this, "Selected: " + select__city, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }


    private void showDialog(String message, Activity context) {
        final Dialog updatedailog = new Dialog(context);
        updatedailog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        updatedailog.setContentView(R.layout.response_success_dialog);
        updatedailog.setCancelable(false);
        updatedailog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        updatedailog.show();
        TextView tvmessage = updatedailog.findViewById(R.id.tv_message);
        tvmessage.setText(message);
        updatedailog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatedailog.dismiss();
                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                intent.putExtra(ApiClass.getmApiClass().COMINGFROM, "RegisterActivity");
                intent.putExtra(ApiClass.getmApiClass().NAME, registerBinding.editFullname.getText().toString());
//                intent.putExtra(ApiClass.getmApiClass().IMAGE, image_base64);
                intent.putExtra(ApiClass.getmApiClass().CITY_ID, selectedCityCode);
                intent.putExtra(ApiClass.getmApiClass().MOBILE, registerBinding.editMobilenumber.getText().toString().trim());
                startActivity(intent);
//                finishAffinity();
//                finish();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void callCityApi() {
        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        ProgressDialog.showProgressDio(RegisterActivity.this);
        Call<CityModel> call = mApiInterface.get_citydata();
        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                ProgressDialog.hideProgressDio();
                try {
                    if (response.isSuccessful()) {
                        CityModel mResponse = response.body();
                        if (mResponse.getStatus().equalsIgnoreCase("success")) {
                            citylist = mResponse.getData();

                        } else if (mResponse.getStatus().equalsIgnoreCase("unsuccess")) {
                            Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.no_reocord_found));

                        }
                    } else {
                        Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.server_error));

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.showSnack(registerBinding.getOtp, getResources().getString(R.string.server_error));

            }
        });
    }

}
