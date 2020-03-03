package com.htl.jsf.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityAboutLayoutBinding;

public class AboutActivity extends AppCompatActivity {

    private ActivityAboutLayoutBinding aboutLayoutBinding;
//    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_layout);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        aboutLayoutBinding.includeToolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        aboutLayoutBinding.includeToolbar.tvTitle.setText("About");
        aboutLayoutBinding.includeToolbar.tvLocation.setVisibility(View.GONE);

//        toolbar = findViewById(R.id.toolbar);
////        activityEventBinding.includeToolbar.findViewById(R.id.toolbar);
//        toolbar.setTitle("About");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_color));
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
