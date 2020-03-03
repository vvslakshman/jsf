package com.htl.jsf.ApiRequest.WithErrorCode;

import android.content.Context;


public class RestClient extends AbstractRestClient
{
//        public static final String BASE_URL = "https://ss.roketvending.com/instamarry/index.php/Matrimony";

        public static final String BASE_URL = "https://ss.roketvending.com/jsf/index.php/Jsf_Api/";


    private Interface DataService;

    public RestClient(Context context) {
        super(context, BASE_URL);
    }

    @Override
    public void initApi() {
        DataService = restAdapter.create(Interface.class);
    }

    public Interface getDataService()
    {
        return DataService;
    }
}
