package com.htl.jsf.Activities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.htl.jsf.Adapters.CityListAdatper;
import com.htl.jsf.Adapters.WinnerAdapter;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.CityDataResponse;
import com.htl.jsf.Models.CityModel;
import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.Models.WinneListResponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityWinnerBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.SuccessDialog;
import com.htl.jsf.utils.Utils;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WinnerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "WinnerActivity=>";
//    RecyclerView recyclerView;
//    Toolbar toolbar;
//    private Menu menu;

    private WinneListResponse generalInfoObject;
    private LoginResponse loginResponse;
    private ActivityWinnerBinding winnerBinding;
    private String city_name, city_id;
    private List<CityDataResponse> citylist;
    private WinnerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        winnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_winner);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        loginResponse = SavePrefrence.getInstance(WinnerActivity.this).getUserdetail();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        winnerBinding.winnerRecyclerView.setLayoutManager(linearLayoutManager);
        winnerBinding.winnerRecyclerView.setHasFixedSize(true);
        winnerBinding.winnerRecyclerView.setVisibility(View.GONE);

        if (loginResponse != null) {
            city_id = loginResponse.getCityId();
            city_name = loginResponse.getCity();
            winnerBinding.includeToolbar.tvLocation.setText(city_name);
            Utils.loge(1, TAG, " onCreate => city_id" + city_id + " cityName" + city_name);
        }

        winnerBinding.includeToolbar.tvTitle.setText(getResources().getString(R.string.winner));
        winnerBinding.includeToolbar.ivBack.setOnClickListener(this);
        winnerBinding.includeToolbar.tvLocation.setOnClickListener(this);

        /* toolbar actions*/
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Winner");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_color));
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//
//            }
//        });
        /////////////////////////

        /*............*/
        if (Utils.isOnline(WinnerActivity.this)) {
            callCityApi();
        } else {
            Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.check_internet_connection));
        }


    }


    private void callCityApi() {
        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        ProgressDialog.showProgressDio(WinnerActivity.this);
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
                            if (Utils.isOnline(WinnerActivity.this)) {
                                call_winner_list();
                            } else {
                                Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.check_internet_connection));
                            }
                        } else if (mResponse.getStatus().equalsIgnoreCase("unsuccess")) {
                            Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.no_reocord_found));

                        }
                    } else {
                        Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.server_error));

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.server_error));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void call_winner_list() {
        if (loginResponse == null) {
            return;
        }
        if (TextUtils.isEmpty(city_id)) {
            return;
        }

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().USER_ID, loginResponse.getUserId());
        jsonObject.addProperty(ApiClass.getmApiClass().AUTH_KEY, loginResponse.getAuthKey());
        jsonObject.addProperty(ApiClass.getmApiClass().CITY_ID, city_id);
        ProgressDialog.showProgressDio(WinnerActivity.this);

        Call<JsonObject> call = mApiInterface.geWinner_list(jsonObject);
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
                            generalInfoObject = gson.fromJson(jsonObjectresponse.toString(), WinneListResponse.class);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WinnerActivity.this);
                            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                            winnerBinding.winnerRecyclerView.setLayoutManager(linearLayoutManager);
                            winnerBinding.winnerRecyclerView.setHasFixedSize(true);
                            adapter = new WinnerAdapter(WinnerActivity.this, generalInfoObject.getData());
                            winnerBinding.winnerRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            winnerBinding.winnerRecyclerView.setVisibility(View.VISIBLE);
                        } else if (jsonObjectresponse.getString("status").equalsIgnoreCase("unsuccess")) {
                            Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                            Utils.showSnack(winnerBinding.winnerRecyclerView, jsonObjectresponse.getString("message"));
                            winnerBinding.winnerRecyclerView.setVisibility(View.GONE);
                            SuccessDialog.showDialog(jsonObjectresponse.getString("message"), WinnerActivity.this);
                        }

                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
                        SuccessDialog.showDialog(getResources().getString(R.string.server_error), WinnerActivity.this);
//                        Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.server_error));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                winnerBinding.winnerRecyclerView.setVisibility(View.GONE);

                Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.server_error));
            }
        });

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        this.menu = menu;
//        MenuTitles();
//        return true;
//    }

//    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.actionl_location) {
//            showChangeDialog(WinnerActivity.this);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void MenuTitles() {
//        if (!TextUtils.isEmpty(city_name)) {
//            MenuItem location_nameMenuItem = menu.findItem(R.id.location_name);
//            location_nameMenuItem.setTitle(city_name);
//
////            ((TextView) location_nameMenuItem).setTextColor(getResources().getColor(R.color.btn_color));
//        }
//    }

    private void updateMenuTitles(String city_name) {
        try {
            if (citylist.size() > 0) {
//                MenuItem location_nameMenuItem = menu.findItem(R.id.location_name);
//                location_nameMenuItem.setTitle(city_name);
                winnerBinding.includeToolbar.tvLocation.setText(city_name);
                if (Utils.isOnline(WinnerActivity.this)) {
                    call_winner_list();
                } else {
                    Utils.showSnack(winnerBinding.winnerRecyclerView, getResources().getString(R.string.check_internet_connection));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void showChangeDialog(final WinnerActivity eventActivity) {
        final Dialog dialog = new Dialog(WinnerActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_citylist_layout);
//        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        RecyclerView rv_city_list = dialog.findViewById(R.id.rv_city_list);

        rv_city_list.setLayoutManager(new LinearLayoutManager(WinnerActivity.this));
        rv_city_list.setAdapter(new CityListAdatper(this, citylist, new CityListAdatper.onCityItemClick() {
            @Override
            public void cityItem(int posi, String city_id, String city_name) {
                eventActivity.city_id = city_id;
                eventActivity.city_name = city_name;
                Utils.loge(1, TAG, "" + "pos" + posi + "\ncityid" + city_id + "\ncityname" + city_name);
                updateMenuTitles(city_name);
                dialog.dismiss();
            }
        }));


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.tv_location:
                showChangeDialog(WinnerActivity.this);
                break;
        }

    }
}
