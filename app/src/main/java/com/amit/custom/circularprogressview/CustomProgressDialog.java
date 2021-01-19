package com.amit.custom.circularprogressview;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

import com.amit.app.R;


public class CustomProgressDialog {

    static ProgressDialog progress;

    public static Dialog showProgressDialog(Context context) {

        Dialog dialog = new Dialog(context, R.style.progress_Custom_Dialog);
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams WMLP = dialog.getWindow().getAttributes();
        WMLP.dimAmount = (float) 0.4;

        return dialog;

//        progress = new ProgressDialog(context);
//        progress.setMessage("Please wait...");
//        progress.addContentView();
//
//        return progress;
    }

    public static void setMessage(String message) {
        progress.setMessage(message);
    }

}
