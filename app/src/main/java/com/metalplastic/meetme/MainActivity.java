package com.metalplastic.meetme;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    public int notifID=0;
    public CheckBox hasTime;

    public RadioGroup radioGroupTimePeriod;

    public View timePeriodDetails;
    public View specificDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasTime = (CheckBox)findViewById(R.id.checkBoxSendTime);
        radioGroupTimePeriod = (RadioGroup)findViewById(R.id.radioButtonTimePeriod);
        timePeriodDetails = findViewById(R.id.layoutTimePeriodDetails);
        specificDateTime = findViewById(R.id.layoutSpecifyTimeDate);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleIncludeTime(View view){
        if( hasTime.isChecked()) {
            timePeriodDetails.setVisibility(View.VISIBLE);
        }
        else {
            timePeriodDetails.setVisibility(View.GONE);
        }
    }

    public void setTimePeriodToNow(View view){
        specificDateTime.setVisibility(View.GONE);
    }
    public void setTimePeriodToSpecific(View view){
        specificDateTime.setVisibility(View.VISIBLE);
    }
}
