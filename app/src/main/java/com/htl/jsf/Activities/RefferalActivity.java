package com.htl.jsf.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.htl.jsf.Adapters.RefferalAdapter;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.Models.RefferalResponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityRefferalBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.SuccessDialog;
import com.htl.jsf.utils.Utils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefferalActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RefferalActivity";


    private ActivityRefferalBinding refferalBinding;
    private RefferalAdapter adapter;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.hideKeyboard(RefferalActivity.this);
        refferalBinding = DataBindingUtil.setContentView(this, R.layout.activity_refferal);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginResponse = SavePrefrence.getInstance(RefferalActivity.this).getUserdetail();


       /* /////////////toolbar actions*/
        refferalBinding.includeToolbar.tvTitle.setText(getResources().getString(R.string.reffer));
        refferalBinding.includeToolbar.tvLocation.setVisibility(View.GONE);
        refferalBinding.includeToolbar.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Refferal");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_color));
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
        /////////////////////////
        ////recyclerview code
//        recyclerView = findViewById(R.id.refferal_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        refferalBinding.refferalRecyclerView.setLayoutManager(layoutManager);
        refferalBinding.refferalRecyclerView.setHasFixedSize(true);

//        List<RefferalModel> refferalModelList = new ArrayList<>();
//        refferalModelList.add(new RefferalModel("101", "08-11-219", "7pm"));
//        refferalModelList.add(new RefferalModel("102", "09-11-219", "8pm"));
//        refferalModelList.add(new RefferalModel("103", "10-11-219", "9pm"));
//         adapter = new RefferalAdapter(RefferalActivity.this, refferalModelList);
//        refferalBinding.refferalRecyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        ///////////////////////

        if (Utils.isOnline(RefferalActivity.this)) {
            callRefferalListApi();
        } else {
            Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.check_internet_connection));
        }


        refferalBinding.btnApply.setOnClickListener(this);
    }




    private void callRefferalListApi() {

        if (loginResponse == null) {
            return;
        }

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().USER_ID, loginResponse.getUserId());
        jsonObject.addProperty(ApiClass.getmApiClass().AUTH_KEY, loginResponse.getAuthKey());
        ProgressDialog.showProgressDio(RefferalActivity.this);

        Call<JsonObject> call = mApiInterface.getCouponsList(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObjectresponse = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);

                    if (response.isSuccessful()) {
                        if (jsonObjectresponse.getString("status").equalsIgnoreCase("success")) {
                            Utils.loge(1, TAG, "Success jsonResponse" + jsonObjectresponse);
                            Gson gson = new Gson();
                            RefferalResponse generalInfoObject = gson.fromJson(jsonObjectresponse.toString(), RefferalResponse.class);
//                            refferalBinding.refferalRecyclerView.setAdapter(new MarketAdapter(generalInfoObject.getData(), ShopsMarketActivity.this));

                            adapter = new RefferalAdapter(RefferalActivity.this, generalInfoObject.getData());
                            refferalBinding.refferalRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            refferalBinding.refferalRecyclerView.setVisibility(View.VISIBLE);


                        } else if (jsonObjectresponse.getString("status").equalsIgnoreCase("unsuccess")) {
                            Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
                            SuccessDialog.showDialog(jsonObjectresponse.getString("message"), RefferalActivity.this);
//                            Utils.showSnack(refferalBinding.refferalRecyclerView, jsonObjectresponse.getString("message"));
                            refferalBinding.refferalRecyclerView.setVisibility(View.GONE);

                        }


                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                        Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.server_error));
                        SuccessDialog.showDialog(getResources().getString(R.string.server_error), RefferalActivity.this);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.server_error));
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_apply:
                if (Utils.isOnline(RefferalActivity.this)) {
                    if (refferalBinding.etCouponCode.getText().toString().isEmpty()) {
//                        Utils.showSnack(refferalBinding.refferalRecyclerView, "Please enter your code");
                        SuccessDialog.showDialog("Please enter your code", RefferalActivity.this);

                    } else {
                        callApplycodeApi();
                    }
                } else {
                    Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.check_internet_connection));
                }
                break;
        }
    }

    private void callApplycodeApi() {
        if (loginResponse == null) {
            return;
        }


        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().USER_ID, loginResponse.getUserId());
        jsonObject.addProperty(ApiClass.getmApiClass().AUTH_KEY, loginResponse.getAuthKey());
//        jsonObject.addProperty(ApiClass.getmApiClass().SHOP_ID, "12");
        jsonObject.addProperty(ApiClass.getmApiClass().COUPON_CODE, refferalBinding.etCouponCode.getText().toString().trim());
        ProgressDialog.showProgressDio(RefferalActivity.this);

        Call<JsonObject> call = mApiInterface.getApplyCoupons(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObjectresponse = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);

                    if (response.isSuccessful()) {
                        if (jsonObjectresponse.getString("status").equalsIgnoreCase("success")) {
                            Utils.loge(1, TAG, "Success jsonResponse" + jsonObjectresponse.getString("message"));
                            showDialog(jsonObjectresponse.getString("message"), RefferalActivity.this);
                        } else if (jsonObjectresponse.getString("status").equalsIgnoreCase("unsuccess")) {
                            Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                            Utils.showSnack(refferalBinding.refferalRecyclerView, jsonObjectresponse.getString("message"));
                            SuccessDialog.showDialog(jsonObjectresponse.getString("message"), RefferalActivity.this);

                        }


                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                        Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.server_error));
                        SuccessDialog.showDialog(getResources().getString(R.string.server_error), RefferalActivity.this);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.showSnack(refferalBinding.refferalRecyclerView, getResources().getString(R.string.server_error));
            }
        });
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
                callRefferalListApi();
            }
        });

    }
}
