package com.example.ibane.bannertest2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClassSchedule extends Activity {
    public ArrayList<JSONObject> class_objects = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setTitle("Schedule");
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.schedule_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ListSpacing(20, 20));

        String incoming = "";

        String method = "schedule";
        BackgroundActivity bgact = new BackgroundActivity(this);
        try {
            incoming = bgact.execute(method).get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        String[] classes = incoming.split("\\|");
        for(int i = 0; i < classes.length; i++) {
            try {
                class_objects.add(new JSONObject(classes[i]));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }


        viewAdapter = new ScheduleAdapter(class_objects);
        recyclerView.setAdapter(viewAdapter);

    }

}


