package com.example.ibane.bannertest2;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.LayoutInflater;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.concurrent.ExecutionException;

/**
 * Created by jesllagr on 9/23/15.
 */
public class StudentTranscript extends Activity {
    ArrayList<JSONObject> class_data = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);
        setTitle("Transcript");
        String[] initialSplit = null;
        String[] courseSplit = null;

        TextView name = (TextView)findViewById(R.id.transcript_student_name);
        TextView studentID = (TextView)findViewById(R.id.transcript_student_id);
        TextView hours = (TextView)findViewById(R.id.transcript_student_hours);
        TextView gpa = (TextView)findViewById(R.id.transcript_student_gpa);
        TextView major = (TextView)findViewById(R.id.transcript_student_major);

        String method = "transcript";
        BackgroundActivity bgact = new BackgroundActivity(this);
        try {
            String data = bgact.execute(method).get();
            initialSplit = data.split("END");
            JSONObject studentData = new JSONObject(initialSplit[1]);

            name.setText(studentData.getString("studentName"));
            studentID.setText(studentData.getString("studentID"));
            hours.setText(studentData.getString("completedHours"));
            gpa.setText(studentData.getString("GPA"));
            major.setText(studentData.getString("name"));

            TableLayout table = (TableLayout)findViewById(R.id.transcript_class_list);

            courseSplit = initialSplit[0].split("\\|");
            for(int i = 0; i < courseSplit.length; i++){
                class_data.add(new JSONObject(courseSplit[i]));
                TableRow tr = (TableRow) LayoutInflater.from(this).inflate(R.layout.table_row_transcript, null);
                ((TextView)tr.findViewById(R.id.tr_course_title)).setText(class_data.get(i).getString("title"));
                ((TextView)tr.findViewById(R.id.tr_hours)).setText(class_data.get(i).getString("hours"));
                ((TextView)tr.findViewById(R.id.tr_grade)).setText(class_data.get(i).getString("grade"));
                ((TextView)tr.findViewById(R.id.tr_term)).setText(class_data.get(i).getString("term"));
                table.addView(tr);
            }

            table.requestLayout();

        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }catch(JSONException e ){
            e.printStackTrace();
        }
    }
}
