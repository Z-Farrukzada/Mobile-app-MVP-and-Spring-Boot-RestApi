package com.zaurfarrukhzada.carannouncementmobileproject.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomToast {

    public View  showMessage(Activity activity, int resource){
        View view  = activity.getLayoutInflater().inflate(resource,null);
        return  view;
    }
    public void setToastTextAndImage(View view, int textId,boolean check, int otherText,String text,int imageId, int toastImage){

        //Toast text and image//
        ImageView imageView = view.findViewById(imageId);
        TextView textView = view.findViewById(textId);
        //Toast text
        if((check) && (otherText == 0)){
            textView.setText(text);
        }
        else if((!check) && (text == null))
        {
            textView.setText(otherText);
        }
        imageView.setImageResource(toastImage);
    }

    public void toastProperty(Activity activity,int gravity,int offsetX,int offsetY,int duration,View view){
        Toast toast = new Toast(activity);
        toast.setGravity(gravity,offsetX,offsetY);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }

}
