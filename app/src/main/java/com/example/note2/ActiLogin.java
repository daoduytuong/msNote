package com.example.note2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActiLogin extends AppCompatActivity {
    Button button;
    TextInputEditText textViewUS;
    TextInputEditText textpass;
    TextView tt;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    public static final String USERNAME = "USERNAME";

    //ActionBar actionBar = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_login);

        button = findViewById(R.id.btnLogin);
        textViewUS = findViewById(R.id.editTextUsername);
        tt = findViewById(R.id.textViewLogin);
        textpass = findViewById(R.id.editTextPassword);
        toolbar = findViewById(R.id.toolbarLogin);

        setSupportActionBar(toolbar);
        //sharedPreferences
        sharedPreferences = getSharedPreferences("HisLog", MODE_PRIVATE);

        AutoLogin();

        //lấy dữ liẹu từ sharedPreferences
        textViewUS.setText(sharedPreferences.getString("username", ""));
        textpass.setText(sharedPreferences.getString("password", ""));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = textViewUS.getText().toString().trim();
                String Pass = textpass.getText().toString().trim();
                new PostToServer(User, Pass).execute("http://tuongdhqn-001-site1.ftempurl.com/postLog.php");
            }
        });
    }

    public void AutoLogin()
    {
        String user = sharedPreferences.getString("username", "");
        String pass = sharedPreferences.getString("password", "");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new  PostToServer(user,pass).execute("http://tuongdhqn-001-site1.ftempurl.com/postLog.php");
            }
        });
    }

    //set menu
    //là góc trên phải chữ SIGN IN
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulogin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // sự kiện cái menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnDki)
        {
            // chuyển màn hình
            Intent intent = new Intent(ActiLogin.this, ActivityDangKy.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
                SharedPreferences.Editor editor = sharedPreferences.edit(); //lưu data vào sharedPreferences
                editor.putString("username", user);
                editor.putString("password", ps);
                editor.commit();

                Intent intent = new Intent(ActiLogin.this, ActivityNote.class); //chuyển màn hình
                intent.putExtra(USERNAME, user);
                startActivity(intent);
            }else if(s.equals("false"))
            {
                //Toast.makeText(ActiLogin.this, "That bai", Toast.LENGTH_LONG).show();
                tt.setText("Đăng nhập thất bại");
            }
            super.onPostExecute(s);
        }
    }
}