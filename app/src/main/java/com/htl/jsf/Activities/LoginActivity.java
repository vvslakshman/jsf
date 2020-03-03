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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.JsonObject;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityLoginBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.Utils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "LoginActivity=>";
    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();


    }

    private void initView() {

        loginBinding.getOtp.setOnClickListener(this);
        loginBinding.signupLink.setOnClickListener(this);


    }

    private void loginValidation() {
        if (TextUtils.isEmpty(loginBinding.editMobilenumber.getText().toString())) {
            Utils.showSnack(loginBinding.getOtp, getResources().getString(R.string.mobile_no_empty));
        } else if (loginBinding.editMobilenumber.getText().length() < 10) {
            Utils.showSnack(loginBinding.getOtp, getResources().getString(R.string.mobiel_length_error));
        } else {

            if (Utils.isOnline(LoginActivity.this)) {
                loginBinding.getOtp.setEnabled(false);
                callLoginApi(loginBinding.editMobilenumber.getText().toString());
            } else {
                Utils.showSnack(loginBinding.getOtp, getResources().getString(R.string.check_internet_connection));

            }
        }

    }

    private void callLoginApi(String mobile) {

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().MOBILE, mobile);
        ProgressDialog.showProgressDio(LoginActivity.this);

        Call<JsonObject> call = mApiInterface.getlogin(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                loginBinding.getOtp.setEnabled(true);
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonObject" + jsonObject);
                    if (response.code() == 200) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
//                            showDialog(getResources().getString(R.string.otp_send_success), LoginActivity.this);
                            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                            intent.putExtra(ApiClass.getmApiClass().COMINGFROM, "LoginActivity");
                            intent.putExtra(ApiClass.getmApiClass().MOBILE, loginBinding.editMobilenumber.getText().toString().trim());
                            startActivity(intent);

                        } else if (jsonObject.getString("status").equalsIgnoreCase("unsuccess")) {
                            showDialog(jsonObject.getString("message"), LoginActivity.this);
                        }
                    } else if (response.code() == 400) {
                        Utils.showSnack(loginBinding.getOtp, "Enter complete user information to save");
                    } else if (response.code() == 409) {
                        Utils.showSnack(loginBinding.getOtp, "Already exist Or already performed");
                    } else if (response.code() == 401) {
                        Utils.showSnack(loginBinding.getOtp, "Wrong Otp Or credentials");
                    } else if (response.code() == 404) {
                        Utils.showSnack(loginBinding.getOtp, "Verfication failed");
                    } else {
                        Utils.showSnack(loginBinding.getOtp, getResources().getString(R.string.server_error));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loginBinding.getOtp.setEnabled(true);
                ProgressDialog.hideProgressDio();
                Utils.log(1, TAG, "onFailure" + t.getLocalizedMessage());
                Utils.showSnack(loginBinding.getOtp, getResources().getString(R.string.server_error));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_otp:
                Utils.hideKeyboard(LoginActivity.this);
                loginValidation();

                break;

            case R.id.signup_link:
//                if (Utils.isOnline(LoginActivity.this)) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//                } else {
//                    Utils.showSnack(getCurrentFocus(), getResources().getString(R.string.check_internet_connection));
//
//                }

                break;
        }
    }


    private void showDialog(final String message, Activity context) {
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
                if (message.equalsIgnoreCase("User Not Registered")) {
                    Utils.log(1, TAG, message + "dialog only dismissed");
                } else {
                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    intent.putExtra(ApiClass.getmApiClass().COMINGFROM, "LoginActivity");
                    intent.putExtra(ApiClass.getmApiClass().MOBILE, loginBinding.editMobilenumber.getText().toString().trim());
                    startActivity(intent);

                }


            }
        });

    }


}


///Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();




