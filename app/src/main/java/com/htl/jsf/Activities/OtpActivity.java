package com.htl.jsf.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.chaos.view.PinView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityOtpBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.SuccessDialog;
import com.htl.jsf.utils.Utils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OtpActivity=> ";
    private PinView pinView;
    private Button btn_verify;
    private TextView resend;
    private String mobile_no, image_base64, full_name, city_id, coming_from;
    private ActivityOtpBinding otpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otpBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();


//        resend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OtpActivity.this, DemoActivity.class));
//            }
//        });

    }

    private void initView() {
//        pinView = findViewById(R.id.pinView);
//        btn_verify = findViewById(R.id.btn_verify);
//        resend = findViewById(R.id.resend);


        if (getIntent().getExtras() != null) {
            mobile_no = getIntent().getStringExtra(ApiClass.getmApiClass().MOBILE);
            otpBinding.tvMobile.setText(mobile_no);
            coming_from = getIntent().getStringExtra(ApiClass.getmApiClass().COMINGFROM);
            if (!TextUtils.isEmpty(coming_from)) {
                if (coming_from.equalsIgnoreCase("RegisterActivity")) {
//                    image_base64 = getIntent().getStringExtra(ApiClass.IMAGE);
                    city_id = getIntent().getStringExtra(ApiClass.getmApiClass().CITY_ID);
                    full_name = getIntent().getStringExtra(ApiClass.getmApiClass().NAME);
                } else {

                }
            }
        }

        otpBinding.btnVerify.setOnClickListener(this);
        otpBinding.resend.setOnClickListener(this);
        otpBinding.ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                Utils.hideKeyboard(OtpActivity.this);
                validatation();
//                startActivity(new Intent(OtpActivity.this, HomeActivity.class));
//                finish();
                break;

            case R.id.resend:
                Utils.hideKeyboard(OtpActivity.this);
                if (Utils.isOnline(OtpActivity.this)) {
                    otpBinding.pinView.setText("");

                    if (coming_from.equalsIgnoreCase("RegisterActivity")) {
                        callResendOtpfor_RegisterApi();
                    } else if (coming_from.equalsIgnoreCase("LoginActivity")) {
                        callResendApiForLogin();
                    }
                } else {
                    Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.check_internet_connection));

                }

                break;

            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    private void callResendApiForLogin() {
        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().MOBILE, mobile_no);
        ProgressDialog.showProgressDio(OtpActivity.this);
//        otpBinding.resend.setEnabled(true);
        Call<JsonObject> call = mApiInterface.getResend(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonObject" + jsonObject);
                    if (response.isSuccessful()) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
//                            showDialog(jsonObject.getString("message"), OtpActivity.this);
//                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
//                            Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.otp_send_success));
                            SuccessDialog.showDialog(getResources().getString(R.string.otp_send_success), OtpActivity.this);

                        } else if (jsonObject.getString("status").equalsIgnoreCase("unsuccess")) {
//                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
                            SuccessDialog.showDialog(jsonObject.getString("message"), OtpActivity.this);
                        }
                    } else if (response.code() == 400) {
                        Utils.showSnack(otpBinding.btnVerify, "Enter complete user information to save");
                    } else if (response.code() == 409) {
                        Utils.showSnack(otpBinding.btnVerify, "Already exist Or already performed");
                    } else if (response.code() == 401) {
                        Utils.showSnack(otpBinding.btnVerify, "Wrong Otp Or credentials");
                    } else if (response.code() == 404) {
                        Utils.showSnack(otpBinding.btnVerify, "Verfication failed");
                    } else {
                        Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.server_error));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.log(1, TAG, "onFailure" + t.getLocalizedMessage());
                SuccessDialog.showDialog(getResources().getString(R.string.server_error), OtpActivity.this);
//                Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.server_error));

            }
        });
    }

    private void callResendOtpfor_RegisterApi() {

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        Utils.loge(1, TAG, "name" + full_name + "\nMoblie" + mobile_no + "\nselectCity" + city_id);
        ProgressDialog.showProgressDio(OtpActivity.this);
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty(ApiClass.getmApiClass().NAME, full_name);
        jsonObject.addProperty(ApiClass.getmApiClass().MOBILE, mobile_no);
//        jsonObject.addProperty(ApiClass.getmApiClass().CITY_ID, city_id);
//        jsonObject.addProperty(ApiClass.getmApiClass().IMAGE, image_base64);
        Call<JsonObject> call = mApiInterface.getResendRegister(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonObject" + jsonObject);
                    if (response.code() == 200) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
//                            showDialog(jsonObject.getString("message"), OtpActivity.this);
//                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
//                            Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.otp_send_success));
                            SuccessDialog.showDialog(getResources().getString(R.string.otp_send_success), OtpActivity.this);
                        } else if (jsonObject.getString("status").equalsIgnoreCase("unsuccess")) {
//                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
                            SuccessDialog.showDialog(jsonObject.getString("message"), OtpActivity.this);
                        }
                    } else if (response.code() == 400) {
                        Utils.showSnack(otpBinding.btnVerify, "Enter complete user information to save");
                    } else if (response.code() == 409) {
                        Utils.showSnack(otpBinding.btnVerify, "Already exist Or already performed");
                    } else if (response.code() == 401) {
                        Utils.showSnack(otpBinding.btnVerify, "Wrong Otp Or credentials");
                    } else if (response.code() == 404) {
                        Utils.showSnack(otpBinding.btnVerify, "Verfication failed");
                    } else {
                        Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.server_error));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.log(1, TAG, "onFailure" + t.getLocalizedMessage());
                Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.server_error));

            }
        });


    }

    private void validatation() {
        if (TextUtils.isEmpty(otpBinding.pinView.getText().toString())) {
            Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.otp_error));
        } else if (!(otpBinding.pinView.getText().toString().length() == 4)) {
            Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.otp_error));
        } else {
            if (Utils.isOnline(OtpActivity.this)) {
                otpBinding.btnVerify.setEnabled(false);
                call_VerifyOtp();
            } else {
                Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.check_internet_connection));
            }
        }
    }

    private void call_VerifyOtp() {

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().MOBILE, mobile_no);
        jsonObject.addProperty(ApiClass.getmApiClass().OTP, otpBinding.pinView.getText().toString().trim());
        ProgressDialog.showProgressDio(OtpActivity.this);
        otpBinding.btnVerify.setEnabled(true);
        Call<JsonObject> call;
        if (coming_from.equalsIgnoreCase("RegisterActivity")) {
            call = mApiInterface.getRegister_verify(jsonObject);
        } else {
            call = mApiInterface.getLogin_verify(jsonObject);
        }

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonObject" + jsonObject);
                    if (response.isSuccessful()) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            Gson gson = new Gson();
                            LoginResponse json = gson.fromJson(jsonObject.toString(), LoginResponse.class);
                            SavePrefrence.getInstance(OtpActivity.this).saveUserdetail(json);
//                            showDialog("User successfully Login", OtpActivity.this);
                            Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finishAffinity();
                            finish();

                        } else if (jsonObject.getString("status").equalsIgnoreCase("unsuccess")) {
//                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
                            SuccessDialog.showDialog(jsonObject.getString("message"), OtpActivity.this);
                        } else {
                            Utils.showSnack(otpBinding.btnVerify, jsonObject.getString("message"));
                            SuccessDialog.showDialog(jsonObject.getString("message"), OtpActivity.this);

                        }
                    } else if (response.code() == 400) {
                        Utils.showSnack(otpBinding.btnVerify, "Enter complete user information to save");
                    } else if (response.code() == 409) {
                        Utils.showSnack(otpBinding.btnVerify, "Already exist Or already performed");
                    } else if (response.code() == 401) {
                        Utils.showSnack(otpBinding.btnVerify, "Wrong Otp Or credentials");
                    } else if (response.code() == 404) {
                        Utils.showSnack(otpBinding.btnVerify, "Verfication failed");
                    } else {
                        Utils.showSnack(otpBinding.btnVerify, getResources().getString(R.string.server_error));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                SuccessDialog.showDialog(t.getMessage(), OtpActivity.this);

            }
        });

    }


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
                Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
                startActivity(intent);
                finishAffinity();
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
