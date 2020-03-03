package com.htl.jsf.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.htl.jsf.R;

public class SuccessDialog {

    public static void showDialog(String message, /*final Activity activity,*/Context context) {
        final Dialog updatedailog = new Dialog(context);
        updatedailog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        updatedailog.setContentView(R.layout.response_success_dialog);
//        updatedailog.setCancelable(false);
        updatedailog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        updatedailog.show();
        TextView tvmessage = updatedailog.findViewById(R.id.tv_message);
        tvmessage.setText(message);
        updatedailog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                activity.startActivity(new Intent(activity, activity.class));
                updatedailog.dismiss();
            }
        });

    }


}
