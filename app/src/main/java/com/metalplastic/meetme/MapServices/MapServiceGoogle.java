package com.metalplastic.meetme.MapServices;

import android.util.Log;

import com.metalplastic.meetme.Interfaces.IWebMapProvider;
import com.metalplastic.meetme.R;


/**
 * Created by nikal on 14 Feb 16.
 */
public class MapServiceGoogle implements IWebMapProvider {
    public int getLogoID(){
        return R.drawable.mapservice_google;
    }
    public String GetName(){
        return "Google Maps";
    }

    public String createMapUrl(double lattitude, double longitude){
        return String.format("http://maps.google.com/?q=%f,%f",lattitude,longitude);
    }
}
