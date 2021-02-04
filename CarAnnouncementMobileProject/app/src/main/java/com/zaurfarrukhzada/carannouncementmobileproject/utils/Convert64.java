package com.zaurfarrukhzada.carannouncementmobileproject.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Convert64 {

    public Bitmap convertBase64Image(String imageUrl){

        byte[] decodedString = Base64.decode(imageUrl, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}
