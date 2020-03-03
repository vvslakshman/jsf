package com.htl.jsf.ApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RetrofitError;


public class ErrorResponseBodyConverter {

    JSONObject jsonObject = null;
    String status = null;
    String message = null;
    BufferedReader reader = null;
    StringBuilder stringBuilder = null;
    String line;

    public ErrorResponseBodyConverter(RetrofitError retrofitError) throws Exception{


        stringBuilder = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(retrofitError.getResponse().getBody().in()));
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(stringBuilder.toString());
            this.status = jsonObject.getString("status");
            this.message = jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


}
