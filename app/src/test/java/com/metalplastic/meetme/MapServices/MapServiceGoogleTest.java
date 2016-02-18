package com.metalplastic.meetme.MapServices;

import com.metalplastic.meetme.Interfaces.IWebMapProvider;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nikal on 14 Feb 16.
 */
public class MapServiceGoogleTest {
    @Test
    public void mapService_GoogleUrlIsValid(){
        IWebMapProvider map = new MapServiceGoogle();

        double latitude = -40.888353;
        double longitude = 175.000459;
        String expectedUrl = String.format("http://maps.google.com/?q=%f,%f", latitude, longitude);

        //Ensuring the right service is loaded
        Assert.assertEquals("Google Maps", map.GetName());

        //Assure URL is built correctly
        Assert.assertEquals(expectedUrl, map.createMapUrl(latitude,longitude));
    }
}