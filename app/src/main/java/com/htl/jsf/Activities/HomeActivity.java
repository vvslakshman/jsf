package com.htl.jsf.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.htl.jsf.Adapters.CityListAdatper;
import com.htl.jsf.Adapters.NavDrawerListAdapter;
import com.htl.jsf.ApiRequest.ApiClass;
import com.htl.jsf.ApiRequest.ApiInterface;
import com.htl.jsf.ApiRequest.RequestClient;
import com.htl.jsf.Models.CityDataResponse;
import com.htl.jsf.Models.CityModel;
import com.htl.jsf.Models.LoginResponse;
import com.htl.jsf.Models.SideNavPojo;
import com.htl.jsf.Models.TodayEventResponse;
import com.htl.jsf.Models.WinneListResponse;
import com.htl.jsf.R;
import com.htl.jsf.databinding.ActivityHomeBinding;
import com.htl.jsf.utils.ProgressDialog;
import com.htl.jsf.utils.SavePrefrence;
import com.htl.jsf.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private final String TAG = "HomeActivity";
    private ImageView btn_today_event;
    private ImageView apply_coupons;
    private ImageView home_shop;
    private ImageView btn_today_winner;
    //    private RecyclerView recyclerView;
    private ImageView btn_sponsor;
    private MenuItem mSpinnerItem1 = null;
    private LoginResponse loginResponse;
    private ActivityHomeBinding homeBinding;
    private TextView tvusername;
    private ImageView profileImage;
    public String city_name, city_id;
    private List<CityDataResponse> citylist;
    private Menu menu;
    private WinneListResponse generalInfoObject;
    private Toolbar toolbar;
    private TextView tv_title, tv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        getretrivecurrentAccessToken();
        toolbar = findViewById(R.id.toolbar);
//        homeBinding.includeAppbarHome.includeContentHome.includeToolbar.tvTitle.setText("Home");
        toolbar.setTitle("");
        tv_title = toolbar.findViewById(R.id.tv_title);
        tv_location = toolbar.findViewById(R.id.tv_location);
        tv_location.setOnClickListener(this);
        tv_title.setText("HOME");

//        toolbar.setTitle("HOME");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.btn_color));
        setSupportActionBar(toolbar);
//        homeBinding.includeAppbarHome.includeContentHome.includeToolbar.tvLocation.setOnClickListener(this);
        loginResponse = SavePrefrence.getInstance(HomeActivity.this).getUserdetail();
        if (loginResponse != null) {
            city_id = loginResponse.getCityId();
            city_name = loginResponse.getCity();
            tv_location.setText(city_name);

//            homeBinding.includeAppbarHome.includeContentHome.includeToolbar.tvLocation.setText(city_name);
//            homeBinding.includeAppbarHome.includeContentHome.includeToolbar.ivBack.setVisibility(View.GONE);
        }
        initView();


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setSideDrawer();
//        navigationView.setNavigationItemSelectedListener(this);
        ///set custom list_icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_arrow);
    }

    private void getretrivecurrentAccessToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.e(TAG, "onComplete: token " + token);
                        calldevicetoken_sendbybackend(token);

                        // Log and toast
                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.e(TAG, "mess with token=> " + msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void calldevicetoken_sendbybackend(String token) {
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
        jsonObject.addProperty("fcm_regid", token);
//        ProgressDialog.showProgressDio(HomeActivity.this);
        Call<JsonObject> call = mApiInterface.getdevice(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObjectresponse = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "calldevicetoken_sendbybackend " + jsonObjectresponse);
                    if (response.isSuccessful()) {
//                        setTodayEventData(jsonObjectresponse);

                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                        Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.loge(1, TAG, "calldevicetoken_sendbybackend" + t.getMessage());
//                Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            exitDialog();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        MenuInflater mi = getMenuInflater();
//        mi.inflate(R.menu.home, menu);
//        this.menu = menu;
////        MenuItem menuItem=menu.findItem(R.id.location_name);
////        Button saveButton = menuItem.getActionView().findViewById(R.id.menuButton);
//
//        MenuTitles(/*saveButton*/);
//        return true;
//    }

//    private void MenuTitles(/*Button saveButton*/) {
//        if (!TextUtils.isEmpty(city_name)) {
//            MenuItem location_nameMenuItem = menu.findItem(R.id.location_name);
////            menu.findItem(R.id.location_name).setTitleColor(Color.RED);
////            location_nameMenuItem.setTooltipText(android.R.col)
//            location_nameMenuItem.setTitle(city_name);
////            saveButton.setText(city_name);
////            ((TextView) location_nameMenuItem).setTextColor(getResources().getColor(R.color.btn_color));
//        }
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.actionl_location || id == R.id.location_name) {
//            showChangeDialog(HomeActivity.this);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void showChangeDialog(final HomeActivity shopsMarketActivity) {
        final Dialog dialog = new Dialog(shopsMarketActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_citylist_layout);
//        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        RecyclerView rv_city_list = dialog.findViewById(R.id.rv_city_list);

        rv_city_list.setLayoutManager(new LinearLayoutManager(shopsMarketActivity));
        rv_city_list.setAdapter(new CityListAdatper(this, citylist, new CityListAdatper.onCityItemClick() {
            @Override
            public void cityItem(int posi, String city_id, String city_name) {
                shopsMarketActivity.city_id = city_id;
                shopsMarketActivity.city_name = city_name;
                Utils.loge(1, TAG, "" + "pos" + posi + "\ncityid" + city_id + "\ncityname" + city_name);
                updateMenuTitles(city_name);
                /*update city and city id in session*/
                LoginResponse session_upate = new LoginResponse();
                session_upate.setCityId(city_id);
                session_upate.setCity(city_name);
                session_upate.setAuthKey(loginResponse.getAuthKey());
                session_upate.setImage(loginResponse.getImage());
//                session_upate.setMessage(loginResponse.getMessage());
                session_upate.setStatus(loginResponse.getStatus());
                session_upate.setUserMobile(loginResponse.getUserMobile());
                session_upate.setUserName(loginResponse.getUserName());
                session_upate.setUserId(loginResponse.getUserId());


                SavePrefrence.getInstance(HomeActivity.this).saveUserdetail(session_upate);
                dialog.dismiss();
            }
        }));


    }

    private void updateMenuTitles(String city_name) {
        try {
            if (citylist.size() > 0) {
//                MenuItem location_nameMenuItem = menu.findItem(R.id.location_name);
//                location_nameMenuItem.setTitle(city_name);
                tv_location.setText(city_name);
//                homeBinding.includeAppbarHome.includeContentHome.includeToolbar.tvLocation.setText(city_name);
                homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceOne.setText(city_name);
                homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceTwo.setText(city_name);
                homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceThree.setText(city_name);
                if (Utils.isOnline(HomeActivity.this)) {
                    call_today_eventApi();
                } else {
                    Utils.showSnack(homeBinding.navView, getResources().getString(R.string.check_internet_connection));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Toast.makeText(HomeActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            finish();
            finishAffinity();
        }
//        else if (id == R.id.nav_coupons) {
//            startActivity(new Intent(HomeActivity.this, CouponsActivity.class));
//
//        }
        else if (id == R.id.nav_about) {
            startActivity(new Intent(HomeActivity.this, AboutActivity.class));
//            Toast.makeText(HomeActivity.this, "About clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_logout) {
            logout_AlertDialog();
        }
        //Toast.makeText(HomeActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();


        // Get the custom view for menu item action view
//        View v = View.inflate(HomeActivity.this,R.layout.custom_menu_action_view,null);
//        item.setActionView(v);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setSideDrawer() {
        homeBinding.listNavigation.setAdapter(new NavDrawerListAdapter(HomeActivity.this, getDrawerItems()));

        homeBinding.listNavigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayDrawerItemView(position);
            }
        });
    }

    private void displayDrawerItemView(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                finish();
                finishAffinity();
                break;

            case 1:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;
            case 2:
                logout_AlertDialog();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    private List<SideNavPojo> getDrawerItems() {
        List<SideNavPojo> drawerListing = new ArrayList<>();
        drawerListing.add(new SideNavPojo(R.drawable.ic_nav_home, "Home"));
        drawerListing.add(new SideNavPojo(R.drawable.about_ic, "About JSF"));
        drawerListing.add(new SideNavPojo(R.drawable.ic_nav_logout, "Logout"));
        return drawerListing;
    }


    private void logout_AlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SavePrefrence.getInstance(HomeActivity.this).removeUserDeatil(HomeActivity.this);
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finishAffinity();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
//                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initView() {
        citylist = new ArrayList<>();
        if (Utils.isOnline(HomeActivity.this)) {
            callCityApi();
        } else {
            Utils.showSnack(homeBinding.navView, getResources().getString(R.string.check_internet_connection));
        }

        //////new code here
//        btn_today_event = findViewById(R.id.btn_today_events);
//        apply_coupons = findViewById(R.id.apply_coupons);
//        home_shop = findViewById(R.id.home_shop);
//        btn_today_winner = findViewById(R.id.btn_today_winner);
//        btn_sponsor = findViewById(R.id.btn_sponsor);
        View header = homeBinding.navView.getHeaderView(0);
        tvusername = header.findViewById(R.id.txt_user);


        if (loginResponse != null) {

            profileImage = header.findViewById(R.id.profile_image);
            tvusername.setText(loginResponse.getUserName());
            Utils.log(1, TAG, "session mobile" + loginResponse.getUserMobile() + "AuthKey" + loginResponse.getAuthKey() + "\nuserid " + loginResponse.getUserId()
                    + "\nCityid" + loginResponse.getCityId());

        }
        Glide.with(HomeActivity.this).load(loginResponse.getImage()).placeholder(R.drawable.icon_circle_image).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(profileImage);


        homeBinding.includeAppbarHome.includeContentHome.btnTodayEvents.setOnClickListener(this);
        homeBinding.includeAppbarHome.includeContentHome.applyCoupons.setOnClickListener(this);
        homeBinding.includeAppbarHome.includeContentHome.homeShop.setOnClickListener(this);
//        homeBinding.includeAppbarHome.includeContentHome.todayWinner.setOnClickListener(this);
        homeBinding.includeAppbarHome.includeContentHome.btnSponsor.setOnClickListener(this);
        homeBinding.includeAppbarHome.includeContentHome.btnTodayWinner.setOnClickListener(this);

        ///clicked operation


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_today_events:
                startActivity(new Intent(HomeActivity.this, EventActivity.class));
                break;

            case R.id.apply_coupons:
                startActivity(new Intent(HomeActivity.this, RefferalActivity.class));
                break;

            case R.id.home_shop:
                startActivity(new Intent(HomeActivity.this, ShopsMarketActivity.class));
                break;

            case R.id.btn_today_winner:
                startActivity(new Intent(HomeActivity.this, WinnerActivity.class));
                break;

            case R.id.btn_sponsor:
                startActivity(new Intent(HomeActivity.this, SponsorActivity.class));
                break;

            case R.id.tv_location:
                showChangeDialog(HomeActivity.this);
                break;


        }
    }


    private void callCityApi() {
        final ApiInterface mApiInterface = RequestClient.getClient(ApiClass.BASE_URL, null).create(ApiInterface.class);
        ProgressDialog.showProgressDio(HomeActivity.this);
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
                            if (Utils.isOnline(HomeActivity.this)) {
                                call_today_eventApi();
                            } else {
                                Utils.showSnack(homeBinding.navView, getResources().getString(R.string.check_internet_connection));
                            }
                        } else if (mResponse.getStatus().equalsIgnoreCase("unsuccess")) {
                            Utils.showSnack(homeBinding.navView, getResources().getString(R.string.no_reocord_found));

                        }
                    } else {
//                        Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
                        Utils.loge(1, TAG, "callCityApi not 200 ");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                ProgressDialog.hideProgressDio();
//                Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
                Utils.loge(1, TAG, "callCityApi Failure " + t.getMessage());

            }
        });
    }

    private void call_today_eventApi() {


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
        ProgressDialog.showProgressDio(HomeActivity.this);

        Call<JsonObject> call = mApiInterface.getToday_event(jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDialog.hideProgressDio();
                try {
                    JSONObject jsonObjectresponse = new JSONObject(String.valueOf(response.body()));
                    Utils.loge(1, TAG, "call_today_eventApi=> jsonResponse" + jsonObjectresponse);
                    if (response.isSuccessful()) {
                        setTodayEventData(jsonObjectresponse);
                    } else {
                        Utils.loge(1, TAG, "call_today_eventApi jsonResponse" + jsonObjectresponse);
//                        Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.loge(1, TAG, "call_today_eventApi OnFailure" + t.getMessage());

//                Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
            }
        });


    }

    private void setTodayEventData(JSONObject jsonObjectResponse) {

//        {
//            "status": "unsuccess",
//                "message": "no data found"
//        }
        homeBinding.includeAppbarHome.includeContentHome.tvEventCity.setText(city_name);
        if (jsonObjectResponse != null) {

            try {
                if (jsonObjectResponse.getString("status").equalsIgnoreCase("success")) {
                    Utils.loge(1, TAG, "Success jsonResponse" + jsonObjectResponse);
                    Gson gson = new Gson();
                    TodayEventResponse generalInfoObject = gson.fromJson(jsonObjectResponse.toString(), TodayEventResponse.class);
//                            homeBinding.includeAppbarHome.includeContentHome.rvTodayWinner.setAdapter(new TodayWinerAdapter(generalInfoObject.getData(),HomeActivity.this));
//                            homeBinding.includeAppbarHome.includeContentHome.rvTodayWinner.setVisibility(View.VISIBLE);

                    homeBinding.includeAppbarHome.includeContentHome.txtMusicNight.setText(generalInfoObject.getData().get(0).getEventName());
                    homeBinding.includeAppbarHome.includeContentHome.artistName.setText(generalInfoObject.getData().get(0).getEventPerformer());
                    homeBinding.includeAppbarHome.includeContentHome.tvEventCity.setText(generalInfoObject.getData().get(0).getEventPerformer());

                } else if (jsonObjectResponse.getString("status").equalsIgnoreCase("unsuccess")) {
                    Utils.loge(1, TAG, "jsonResponse" + jsonObjectResponse);
//                    Utils.showSnack(homeBinding.navView, jsonObjectResponse.getString("message"));
                    if (jsonObjectResponse.getString("message").equalsIgnoreCase("no data found")) {
                        homeBinding.includeAppbarHome.includeContentHome.txtMusicNight.setText(getResources().getString(R.string.no_event));
                        homeBinding.includeAppbarHome.includeContentHome.tvEventCity.setText(city_name);
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (Utils.isOnline(HomeActivity.this)) {
            call_winner_list();
        } else {
            Utils.showSnack(homeBinding.navView, getResources().getString(R.string.check_internet_connection));
        }

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
        ProgressDialog.showProgressDio(HomeActivity.this);

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
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this);
//                            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//                            homeBinding.includeAppbarHome.includeContentHome.rvTodayWinner.setLayoutManager(linearLayoutManager);
//                            homeBinding.includeAppbarHome.includeContentHome.rvTodayWinner.setHasFixedSize(true);
//                            homeBinding.includeAppbarHome.includeContentHome.rvTodayWinner.setAdapter(new TodayWinerAdapter(generalInfoObject.getData(), HomeActivity.this));

                            Utils.loge(1, TAG, "listSize" + generalInfoObject.getData().size());
                            if (generalInfoObject.getData().size() > 0) {
                                if (generalInfoObject.getData().size() == 1) {
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayOne.setText(generalInfoObject.getData().get(0).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeOne.setText(generalInfoObject.getData().get(0).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceOne.setText(generalInfoObject.getData().get(0).getCity());

                                } else if (generalInfoObject.getData().size() == 2) {
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayOne.setText(generalInfoObject.getData().get(0).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeOne.setText(generalInfoObject.getData().get(0).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceOne.setText(generalInfoObject.getData().get(0).getCity());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayTwo.setText(generalInfoObject.getData().get(1).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeTwo.setText(generalInfoObject.getData().get(1).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceTwo.setText(generalInfoObject.getData().get(1).getCity());

                                } else if (generalInfoObject.getData().size() >= 3) {
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayOne.setText(generalInfoObject.getData().get(0).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeOne.setText(generalInfoObject.getData().get(0).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceOne.setText(generalInfoObject.getData().get(0).getCity());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayTwo.setText(generalInfoObject.getData().get(1).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeTwo.setText(generalInfoObject.getData().get(1).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceTwo.setText(generalInfoObject.getData().get(1).getCity());

                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayThree.setText(generalInfoObject.getData().get(2).getName());
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeThree.setText(generalInfoObject.getData().get(2).getPosition() + "Prize");
                                    homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceThree.setText(generalInfoObject.getData().get(2).getCity());
                                }

//                                Glide.with(HomeActivity.this).load(generalInfoObject.getData().get()).placeholder(R.drawable.profile_img).dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.show_image);


//                                holder.winner_today.setText(myModel.getName());
//                                holder.winner_today_place.setText(myModel.getCity());
//                                holder.winner_today_prize.setText(myModel.getPosition());
                            }


                        } else if (jsonObjectresponse.getString("status").equalsIgnoreCase("unsuccess")) {
                            Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                            Utils.showSnack(homeBinding.navView, jsonObjectresponse.getString("message"));

                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayOne.setText(getResources().getString(R.string.winner_1));
                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeOne.setText(getResources().getString(R.string._1st_prize));

                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayTwo.setText(getResources().getString(R.string.winner_2));
                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeTwo.setText(getResources().getString(R.string._2nd_prize));

                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayThree.setText(getResources().getString(R.string.winner_3));
                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPrizeThree.setText(getResources().getString(R.string._3rd_prize));

                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceOne.setText(city_name);
                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceTwo.setText(city_name);
                            homeBinding.includeAppbarHome.includeContentHome.winnerTodayPlaceThree.setText(city_name);


                        }

                    } else {
                        Utils.loge(1, TAG, "jsonResponse" + jsonObjectresponse);
//                        Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDialog.hideProgressDio();
                Utils.loge(1, TAG, "onFailure" + t.getMessage());

//                Utils.showSnack(homeBinding.navView, getResources().getString(R.string.server_error));
            }
        });

    }
//    {"status":"unsuccess","message":"No Data Found"}

}


//nav_drawer = findViewById(R.id.nav_drawer);
//
//
//        nav_drawer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawer.isDrawerOpen(Gravity.LEFT)) {
//                    drawer.closeDrawer(Gravity.LEFT);
//                } else {
//                    drawer.openDrawer(Gravity.LEFT);
//                }
//            }
//        });