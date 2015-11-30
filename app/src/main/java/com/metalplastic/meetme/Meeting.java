package com.metalplastic.meetme;

import android.graphics.Point;
import android.provider.ContactsContract;

import java.sql.Time;
import java.util.List;

/**
 * Created by NikolaiA on 28 Nov 15.
 */
public class Meeting {

    private String description;
    private List<ContactsContract.Contacts> contacts;
    public Time meetingTime;
    private Point address;

    public List<ContactsContract.Contacts> getContacts() {
        return contacts;
    }
    public String getDescription(){
        return this.description;
    }
    public boolean hasAddress(){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public boolean hasTime(){
        return ( meetingTime != null );
    }

    public String invitation(){
        return "Placeholder message";
    }
}
