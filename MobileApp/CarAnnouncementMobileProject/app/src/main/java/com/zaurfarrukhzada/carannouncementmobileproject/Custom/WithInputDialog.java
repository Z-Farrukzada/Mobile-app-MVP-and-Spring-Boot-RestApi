package com.zaurfarrukhzada.carannouncementmobileproject.Custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WithInputDialog {

    private Activity activity;
    private  AlertDialog dialog;

    public AlertDialog showDialog(Activity  activity,int resource){
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(resource,null,false);

        AlertDialog alertDialog = new AlertDialog.Builder(activity).setView(view).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        return alertDialog;
    }


}
