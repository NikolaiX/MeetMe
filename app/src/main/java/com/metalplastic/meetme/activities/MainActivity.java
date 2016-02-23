package com.metalplastic.meetme.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.metalplastic.meetme.MeetingBuilder;
import com.metalplastic.meetme.R;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    public int notifID=0;

    public CheckBox hasLocation;
    public CheckBox hasWrittenAddress;

    public View timePeriodDetails;
    public View specificDateTime;
    public CheckBox hasTime;
    public RadioGroup radioGroupTimePeriod;



    public TextView TextViewTimeDate;

    MeetingBuilder meetingBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingBuilder = new MeetingBuilder();

        hasTime = (CheckBox)findViewById(R.id.checkBoxSendTime);
        hasLocation = (CheckBox)findViewById(R.id.checkBoxSendLocation);
        radioGroupTimePeriod = (RadioGroup)findViewById(R.id.radioButtonTimePeriod);
        timePeriodDetails = findViewById(R.id.layoutTimePeriodDetails);
        specificDateTime = findViewById(R.id.layoutSpecifyTimeDate);
        TextViewTimeDate = (TextView)findViewById(R.id.textViewDateTime);
        hasWrittenAddress = (CheckBox)findViewById(R.id.checkBoxWrittenAddress);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("Connection", String.valueOf(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleIncludeTime(View view){
        if( hasTime.isChecked()) {
            Log.i("UI", "Time checkbox has been checked");
            timePeriodDetails.setVisibility(View.VISIBLE);
        }
        else {
            Log.i("UI", "Time checkbox has been unchecked");
            timePeriodDetails.setVisibility(View.GONE);
        }
    }
    public void toggleIncludeLocation(View view){
        if( hasLocation.isChecked()) {
            Log.i("UI", "Location checkbox has been checked");
            selectLocationDialog();
            timePeriodDetails.setVisibility(View.VISIBLE);
        }
        else {
            Log.i("UI", "Location checkbox has been unchecked");
            timePeriodDetails.setVisibility(View.GONE);
        }
    }

    public void changeTime(View view){

        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextViewTimeDate.setText(String.format("Date, %d:%d", hourOfDay, minute));
            }
        }, getCurrentTimeHour(),getCurrentTimeMinute(),false);

        tpd.show();
    }
    public void changeDate(View view){

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TextViewTimeDate.setText(String.format("%d/%d/%d, TIME",dayOfMonth,monthOfYear,year));
            }
        }, getCurrentDateYear(),getCurrentDateMonth(),getCurrentDateDay());

        dpd.show();
    }
    public int getCurrentTimeHour(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR);
    }
    public int getCurrentTimeMinute(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);
    }
    public int getCurrentDateYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    public int getCurrentDateMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }
    public int getCurrentDateDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }
    public void setTimePeriodToNow(View view){
        specificDateTime.setVisibility(View.GONE);
    }
    public void setTimePeriodToSpecific(View view){
        specificDateTime.setVisibility(View.VISIBLE);
    }

    public void selectLocationDialog(){
        PlacePicker.IntentBuilder mPicker = new PlacePicker.IntentBuilder();
        Log.i("UI", "Location Picker has been created");

        try {
            startActivityForResult(mPicker.build(this), PLACE_PICKER_REQUEST);
            Log.i("UI", "Location Picker Activity has Completed");
        }
        catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            Log.e("Exception", "Repairable Exception", e);
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
            Log.e("Exception", "Google Play Services not available Exception", e);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place newPlace = PlacePicker.getPlace(this, data);
                meetingBuilder.setPlace(newPlace);

                if(newPlace.getAddress().length() == 0) {
                    Log.i("Information", "No written address included in place");
                    disableWrittenAddress();
                }
                else {
                    Log.i("Information", "Address recorded: '" + newPlace.getAddress() + "' ");
                    enableWrittenAddress();
                }

                String toastMsg = String.format("Place: %s", newPlace.getAddress());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

            }
        }
    }
    private void disableWrittenAddress(){
        hasWrittenAddress.setChecked(false);
        hasWrittenAddress.setEnabled(false);
        meetingBuilder.excludeWrittenAddress();
    }
    private void enableWrittenAddress(){
        hasWrittenAddress.setChecked(true);
        hasWrittenAddress.setEnabled(true);
        meetingBuilder.includeWrittenAddress();
    }
    public void toggleWrittenAddress(View view){
        if( hasWrittenAddress.isChecked() )
            meetingBuilder.includeWrittenAddress();
        else
            meetingBuilder.excludeWrittenAddress();

    }
}
