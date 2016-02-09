package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by jesllagr on 10/15/15.
 */
public class StudentInfo extends Activity{
    String data_string = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int usrid = sharedPref.getInt("user_id", 0);

//        final TextView name = (TextView)findViewById(R.id.student_name);
        final TextView ident_num = (TextView)findViewById(R.id.student_id);
        final TextView majors = (TextView)findViewById(R.id.student_major);
        final TextView email = (TextView)findViewById(R.id.student_email);

        String method = "get_info";
        BackgroundActivity bgact = new BackgroundActivity(this);
        try {
            data_string = bgact.execute(method).get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        //while(data_string == null) {}

            try {
                JSONObject data = new JSONObject(data_string);
                setTitle(data.getString("studentName"));
//                name.setText(data.getString("studentName"));
                ident_num.setText(data.getString("studentID"));
                majors.setText(data.getString("major_name"));
                email.setText(data.getString("username") + "@ut.utm.edu");
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    public void returnData(String value){
        data_string = value;
    }
}
