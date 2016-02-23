package com.metalplastic.meetme;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.metalplastic.meetme.data.Meeting;

import java.sql.Time;
/**
 * Created by NikolaiA on 28 Nov 15.
 */
public class MeetingBuilder {

    private Place thePlace;

    private Boolean hasWrittenAddress = false;

    public Meeting build(){
        throw new NotImplementedException();
    }

    public void setPlace(Place place){
        thePlace = place;
    }
    private LatLng getLatLong(){
        return thePlace.getLatLng();
    }



    public void includeWrittenAddress(){
        if(thePlace.getAddress() != null)
            hasWrittenAddress = true;
    }

    public void excludeWrittenAddress(){
        hasWrittenAddress = false;
    }
}
