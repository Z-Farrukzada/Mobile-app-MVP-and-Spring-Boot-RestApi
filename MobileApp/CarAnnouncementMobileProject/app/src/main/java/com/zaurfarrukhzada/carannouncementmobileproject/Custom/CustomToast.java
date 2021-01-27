package com.zaurfarrukhzada.carannouncementmobileproject.Custom;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zaurfarrukhzada.carannouncementmobileproject.R;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomToast {

    public View  showMessage(Activity activity, int resource){
        View view  = activity.getLayoutInflater().inflate(resource,null);
        return  view;
    }
    public void setToastTextAndImage(View view, int textId, int text,int imageId,int toastImage){
        //Toast text and image//
        ImageView imageView = view.findViewById(imageId);
        TextView textView = view.findViewById(textId);
        //Toast text
        imageView.setImageResource(toastImage);
        textView.setText(text);
    }

    public void toastProperty(Activity activity,int gravity,int offsetX,int offsetY,int duration,View view){
        Toast toast = new Toast(activity);
        toast.setGravity(gravity,offsetX,offsetY);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }

}
