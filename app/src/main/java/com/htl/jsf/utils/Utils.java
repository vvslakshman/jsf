package com.htl.jsf.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

public class Utils {


    public static void log(int i, String retrofit, String s) {
        Log.v(retrofit, s.toString());
    }

    public static void loge(int i, String retrofit, String s) {
        Log.e(retrofit, s.toString());
    }

    public static void logd(int i, String retrofit, String s) {
        Log.d(retrofit, s.toString());
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null)
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }
        else
            return false;
    }


    @SuppressLint("NewApi")
    public static void showSnack(View view, String msg) {
        if (view != null && view.isAttachedToWindow())
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }


    public static void hideKeyboard(Activity _activity) {
        View view = _activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) _activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
