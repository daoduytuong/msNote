package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivitySetting extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    class PostToServer extends AsyncTask<String, Void, String>
    {
        OkHttpClient client = new OkHttpClient();
        String user;
        String ps;

        public PostToServer(String user, String ps) {
            this.user = user;
            this.ps = ps;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("username", user)
                    .addFormDataPart("password", ps)
                    .setType(MultipartBody.FORM)
                    .build();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return  response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.equals("true"))
            {
                // Toast.makeText(ActiLogin.this, "Thanh cong", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ActivitySetting.this, ActivityNote.class); //chuyển màn hình
                intent.putExtra(USERNAME, user);
                startActivity(intent);
            }else if(s.equals("false"))
            {
                //Toast.makeText(ActiLogin.this, "That bai", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivitySetting.this, ActiLogin.class); //chuyển màn hình
                startActivity(intent);
            }
            super.onPostExecute(s);
        }
    }
}