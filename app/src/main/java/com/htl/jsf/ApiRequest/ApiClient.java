package com.htl.jsf.ApiRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String City_list = "city_list";
    public static final String Register = "register";

//    public static final String User_Signup = "register_user.php";


    private static final String BASE_URL = "https://ss.roketvending.com/jsf/index.php/Jsf_Api/";
    private static ApiInterface retrofit = null;

    public static ApiInterface getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface.class);
        }
        return retrofit;
    }
}
