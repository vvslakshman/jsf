package com.htl.jsf.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.htl.jsf.Adapters.SponsorPatnerAdapter;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.Models.SponserPartnerReponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivitySponsorBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.SuccessDialog;
import com.htl.jsf.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.jaredrummler.materialspinner.MaterialSpinner;

public class SponsorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = "";


    private ActivitySponsorBinding sponsorBinding;
    private LoginResponse loginResponse;
    public String city_name, city_id;
    private SponsorPatnerAdapter adapter;
    //    private SponsorAdapter adapter;
    ArrayList<String> sponser_typelist = new ArrayList<>();
    private SponserPartnerReponse generalInfoObject;

    private List<SponserPartnerReponse.SponserData> gold_list = new ArrayList<>();
    private List<SponserPartnerReponse.SponserData> silver_list = new ArrayList<>();
    private ArrayAdapter<String> spinneradapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sponsorBinding = DataBindingUtil.setContentView(this, R.layout.activity_sponsor);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginResponse = SavePrefrence.getInstance(SponsorActivity.this).getUserdetail();
        if (loginResponse != null) {
            city_id = loginResponse.getCityId();
            city_name = loginResponse.getCity();
//            sponsorBinding.includeToolbar.tvLocation.setText(city_name);
        }

        sponsorBinding.includeToolbar.tvTitle.setText(getResources().getString(R.string.sponsar));
        sponsorBinding.includeToolbar.tvLocation.setVisibility(View.GONE);

//        sponsorBinding.includeToolbar.tvLocation.setOnClickListener(this);
        sponsorBinding.includeToolbar.ivBack.setOnClickListener(this);
        /* /////////////toolbar actions*/
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Sponsor's");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_color));
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//
//            }
//        });
        /*...........*/
        sponsorBinding.spinnerCategory.setOnItemSelectedListener(this);

        sponser_typelist.add(0, "All");
        sponser_typelist.add(1, "Gold");
        sponser_typelist.add(2, "Silver");
        spinneradapter = new ArrayAdapter<>(SponsorActivity.this, R.layout.spinne_item_sponsor, sponser_typelist);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sponsorBinding.spinnerCategory.setAdapter(spinneradapter);

        /////////////////////////

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        sponsorBinding.sponsorRecyclerView.setLayoutManager(linearLayoutManager);
        sponsorBinding.sponsorRecyclerView.setHasFixedSize(true);

    }

    private void showlist_ascategory(String items_show) {
        try {
            if (items_show.equalsIgnoreCase("Gold")) {
                adapter = new SponsorPatnerAdapter(SponsorActivity.this, gold_list);
                sponsorBinding.sponsorRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                sponsorBinding.sponsorRecyclerView.setVisibility(View.VISIBLE);
            } else {

                adapter = new SponsorPatnerAdapter(SponsorActivity.this, silver_list);
                sponsorBinding.sponsorRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                sponsorBinding.sponsorRecyclerView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void callShopsListApi() {
        final LoginResponse loginResponse = SavePrefrence.getInstance(SponsorActivity.this).getUserdetail();

        if (loginResponse == null) {
            return;
        }
//        if (TextUtils.isEmpty(city_id)) {
//            return;
//        }

        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiClass.getmApiClass().USER_ID, loginResponse.getUserId());
        jsonObject.addProperty(ApiClass.getmApiClass().AUTH_KEY, loginResponse.getAuthKey());
//        jsonObject.addProperty(ApiClass.getmApiClass().CITY_ID, city_id);
        ProgressDialog.showProgressDio(SponsorActivity.this);

        Call<JsonObject> call = mApiInterface.getsponserPartnerList(jsonObject);
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
                            generalInfoObject = gson.fromJson(jsonObjectresponse.toString(), SponserPartnerReponse.class);
                            adapter = new SponsorPatnerAdapter(SponsorActivity.this, generalInfoObject.getData());
                            sponsorBinding.sponsorRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
//                            sponsorBinding.sponsorRecyclerView.setAdapter(new MarketAdapter(generalInfoObject.getData(), SponsorActivity.this));
                            sponsorBinding.sponsorRecyclerView.setVisibility(View.VISIBLE);
                            if (generalInfoObject != null) {
                                addDataInSpinner(generalInfoObject);
                            }

                        } else if (jsonObjectresponse.getString("status").equalsIgnoreCase("unsuccess")) {
                            Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
                            Utils.showSnack(sponsorBinding.sponsorRecyclerView, jsonObjectresponse.getString("message"));
                            sponsorBinding.sponsorRecyclerView.setVisibility(View.GONE);
                            SuccessDialog.showDialog(jsonObjectresponse.getString("message"), SponsorActivity.this);
                        }

                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
                        Utils.showSnack(sponsorBinding.sponsorRecyclerView, getResources().getString(R.string.server_error));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.showSnack(sponsorBinding.sponsorRecyclerView, getResources().getString(R.string.server_error));
            }
        });


    }

    private void addDataInSpinner(SponserPartnerReponse generalInfoObject) {
        for (int i = 0; i < generalInfoObject.getData().size(); i++) {
            if (generalInfoObject.getData().get(i).getSponserType().equalsIgnoreCase("Gold")) {
                gold_list.add(generalInfoObject.getData().get(i));
            } else if (generalInfoObject.getData().get(i).getSponserType().equalsIgnoreCase("Silver")) {
                silver_list.add(generalInfoObject.getData().get(i));

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getItemAtPosition(position).equals("All")) ;

        String sponsor_type = adapterView.getItemAtPosition(position).toString();
        if (sponsor_type.equalsIgnoreCase("All")) {
            if (Utils.isOnline(SponsorActivity.this)) {
                callShopsListApi();
            } else {
                Utils.showSnack(sponsorBinding.sponsorRecyclerView, getResources().getString(R.string.check_internet_connection));
            }

        }
        if (generalInfoObject != null) {
            if (sponsor_type.equalsIgnoreCase("Gold")) {
                showlist_ascategory(sponsor_type);
            } else if (sponsor_type.equalsIgnoreCase("Silver")) {
                showlist_ascategory(sponsor_type);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }
}
