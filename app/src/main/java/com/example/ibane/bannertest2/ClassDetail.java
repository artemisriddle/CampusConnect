package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jesllagr on 10/19/15.
 */
public class ClassDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        setTitle(sharedPref.getString("courseNumber", ""));

        TextView courseTitle = (TextView)findViewById(R.id.class_detail_header);
        courseTitle.setText(sharedPref.getString("courseTitle","") + " " + sharedPref.getString("sectionNumber", ""));

        TextView instructorName = (TextView)findViewById(R.id.class_detail_instructor);
        instructorName.setText(sharedPref.getString("teacherName",""));

        TextView location = (TextView)findViewById(R.id.class_detail_location);
        location.setText(sharedPref.getString("location",""));

        TextView day = (TextView)findViewById(R.id.class_detail_days);
        day.setText(sharedPref.getString("day",""));

        TextView start = (TextView)findViewById(R.id.class_time);
        start.setText(convertTime(sharedPref.getString("startTime","")) + " - " + convertTime(sharedPref.getString("endTime","")));

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(sharedPref.getString("description",""));
    }

    private String convertTime (String time){
        DateFormat f1 = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = f1.parse(time);
        }catch(ParseException e){
            e.printStackTrace();
        }

        DateFormat f2 = new SimpleDateFormat("hh:mm a");
        return f2.format(d);
    }
}
