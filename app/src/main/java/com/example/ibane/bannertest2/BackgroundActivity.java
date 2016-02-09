//async task, handles all server communication


package com.example.ibane.bannertest2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ibane on 9/29/2015.
 */
public class BackgroundActivity extends AsyncTask<String, Void, String> {
    Context ctx;
    AlertDialog alertDialog;
    private String method = null;

    BackgroundActivity(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information.");
    }

    @Override
    protected String doInBackground(String... params) {
        //runs script based on params
        method = params[0];
        if (method.equals("login")){
            String url = "http://cs1.utm.edu/~jesllagr/login.php";
            return loginFn(params[1], params[2], url);
        }
        else if (method.equals("get_info")){
            String url = "http://cs1.utm.edu/~jesllagr/student_info.php";
            return infoFn(url);
        }
        else if (method.equals("schedule")){
            String url = "http://cs1.utm.edu/~jesllagr/schedule.php";
            return scheduleFn(url);
        }
        else if(method.equals("transcript")){
            String url = "http://cs1.utm.edu/~jesllagr/transcript.php";
            return transcriptFn(url);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.contains("Login")){
            String[] info = result.split("\\|");
            Toast.makeText(ctx, info[0], Toast.LENGTH_SHORT).show();
            if(result.contains("success")){
                int id = Integer.parseInt(info[1]);
                final SharedPreferences sharedPref = PreferenceManager. getDefaultSharedPreferences(ctx);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("user_id", id);
                editor.commit();
                Intent intent = new Intent(ctx, MainMenu.class);
                ctx.startActivity(intent);
            }
        }
    }

    private String loginFn(String login_name, String login_pass, String login_url){
        try {
            //handles server login
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("login_name","UTF-8") + "=" + URLEncoder.encode(login_name,"UTF-8") + "&" +
                    URLEncoder.encode("login_pass","UTF-8") + "=" + URLEncoder.encode(login_pass,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String infoFn(String access_url){
        try {
            //stores user info
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
            int usrid = sharedPref.getInt("user_id", 0);

            URL url = new URL(access_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("student_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(usrid),"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String scheduleFn(String access_url){
        try {
            // retrieves student schedule
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
            int usrid = sharedPref.getInt("user_id", 0);


            URL url = new URL(access_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("student_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(usrid),"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String transcriptFn(String access_url){
        try {
            //retrieves student transcript
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
            int usrid = sharedPref.getInt("user_id", 0);


            URL url = new URL(access_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("student_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(usrid),"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
