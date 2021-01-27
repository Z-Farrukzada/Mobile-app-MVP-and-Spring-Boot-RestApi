package com.zaurfarrukhzada.carannouncementmobileproject.Custom;


import android.app.Activity;
import android.app.AlertDialog;


import com.zaurfarrukhzada.carannouncementmobileproject.R;

import lombok.experimental.UtilityClass;

@UtilityClass
public  class LoadingDialogCustom {


    private AlertDialog dialog;


    public  void startDialog(Activity activity){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setView(activity.getLayoutInflater().inflate(R.layout.custom_dialog,null,false));
        alertDialog.setCancelable(false);
        dialog = alertDialog.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
