package com.metalplastic.meetme;

import com.metalplastic.meetme.data.Meeting;

import java.sql.Time;
/**
 * Created by NikolaiA on 28 Nov 15.
 */
public class MeetingBuilder {
    public Meeting theMeeting;

    public void setTime(Time theTime){
        theMeeting.meetingTime = theTime;
    }
}
