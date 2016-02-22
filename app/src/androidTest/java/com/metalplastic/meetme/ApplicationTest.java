package com.metalplastic.meetme;

import android.app.Application;
import android.preference.Preference;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.metalplastic.meetme.activities.SettingsActivity;

import junit.framework.Assert;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */


public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

    }
}