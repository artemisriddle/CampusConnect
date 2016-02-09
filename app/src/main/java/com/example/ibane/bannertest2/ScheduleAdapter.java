package com.example.ibane.bannertest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jesllagr on 10/17/15.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    ArrayList<JSONObject> data;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView classTitle;
        public TextView location;
        public TextView day;
        public TextView time;
        private Context context;
        public ArrayList<JSONObject> data;

        public ViewHolder(View itemView) {
            super(itemView);
            classTitle = (TextView) itemView.findViewById(R.id.classTitle);
            location = (TextView) itemView.findViewById(R.id.classLocation);
            day = (TextView) itemView.findViewById(R.id.classDay);
            time = (TextView) itemView.findViewById(R.id.classTime);
            context = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        public void setData(ArrayList<JSONObject> data) {
            this.data = data;
        }

        @Override
        public void onClick(View view) {

            try {
                JSONObject selected = data.get(getLayoutPosition());
                Toast.makeText(context, selected.getString("title"), Toast.LENGTH_SHORT);
                SharedPreferences sharedPref = PreferenceManager. getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("courseNumber", selected.getString("courseNumber"));
                editor.putString("sectionNumber", selected.getString("sectionNumber"));
                editor.putString("courseTitle", selected.getString("title"));
                editor.putString("teacherName", selected.getString("teacherName"));
                editor.putString("location", selected.getString("location"));
                editor.putString("day", selected.getString("day"));
                editor.putString("startTime",selected.getString("startTime"));
                editor.putString("endTime", selected.getString("endTime"));
                editor.putString("description", selected.getString("description"));
                editor.commit();

                context.startActivity(new Intent(context, ClassDetail.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public ScheduleAdapter(ArrayList<JSONObject> data) {
        this.data = data;

    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.classlist, parent, false);

        ViewHolder vh = new ViewHolder(v);
        vh.setData(data);
        return vh;
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        try {
            JSONObject object = data.get(position);
            holder.classTitle.setText(object.getString("courseNumber"));
            holder.location.setText(object.getString("location"));
            holder.day.setText(object.getString("day"));
            holder.time.setText(convertTime(object.getString("startTime")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String convertTime(String time) {
        DateFormat f1 = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = f1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat f2 = new SimpleDateFormat("hh:mm a");
        return f2.format(d);
    }
}
