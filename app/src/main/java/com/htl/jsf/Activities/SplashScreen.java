package com.htl.jsf.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivitySplashScreenBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.Utils;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen=>";
    private int splashtimeout = 3000;


    private ActivitySplashScreenBinding splashScreenBinding;


    @Override
    protected void onResume() {
        super.onResume();
        try {
            ProgressDialog.showProgressDio(SplashScreen.this);
            iniateView();
            Utils.loge(1, TAG, "onResume");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

    }

    private void iniateView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialog.hideProgressDio();
                try {
                    LoginResponse loginResponse = SavePrefrence.getInstance(SplashScreen.this).getUserdetail();
                    Utils.loge(1, TAG, "run: " + loginResponse);
//                    if (Utils.isOnline(SplashScreen.this)) {
                        if (loginResponse != null) {
                            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                            finish();
                        }

//                    } else {
//                        Utils.showSnack(splashScreenBinding.ivLogo, getResources().getString(R.string.check_internet_connection));
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, splashtimeout);
    }


}
