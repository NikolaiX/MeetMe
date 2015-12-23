package com.metalplastic.meetme;

import java.util.List;

/**
 * Created by NikolaiA on 30 Nov 15.
 */
public class MeetingHandler {
    private static MeetingHandler instance;
    private List<Meeting> meetingHistory;
    public static MeetingHandler Access(){
        return new MeetingHandler();
    }
    public void sendInvitation(Meeting theMeeting){
        meetingHistory.add(theMeeting);

        String invitation = theMeeting.invitation();
    }
    public List<Meeting> GetMeetingHistory(){
        return meetingHistory;
    }
}
