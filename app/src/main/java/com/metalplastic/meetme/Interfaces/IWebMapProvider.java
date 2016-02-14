package com.metalplastic.meetme.Interfaces;

/**
 * Created by nikal on 14 Feb 16.
 * Allows for implementation of different map providers
 */
public interface IWebMapProvider {
    int getLogoID();
    String GetName();

    String createMapUrl(double lattitude, double longitude);
}
