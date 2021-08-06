package com.nayan.thirdparty;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog {

    static ProgressDialog progress;

    public static Dialog showProgressDialog(Context context) {

        progress = new ProgressDialog(context);
        progress.setMessage("Please wait...");

        return progress;
    }

    public static void setMessage(String message) {
        progress.setMessage(message);
    }
}
