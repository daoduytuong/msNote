package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityDangKy extends AppCompatActivity {
    Button button;
    TextInputEditText textViewUS;
    TextInputEditText pass;
    TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        button = findViewById(R.id.btnRegister);
        textViewUS = findViewById(R.id.editTextUsernameReg);
        pass = findViewById(R.id.editTextPasswordReg);
        tt = findViewById(R.id.textViewReg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = textViewUS.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                new PostToServer(User, Pass).execute("http://192.168.1.2/msNote/postReg.php");
            }
        });

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

            if(s.equals("SUCCESS"))
            {
                Toast.makeText(ActivityDangKy.this, "Dang ky thanh cong", Toast.LENGTH_LONG).show();
                tt.setText("Tạo tài khoản thành công");

            }

            if(s.equals("ERROR01"))
            {
                Toast.makeText(ActivityDangKy.this, "Dang ky that bai", Toast.LENGTH_LONG).show();
                tt.setText(s.toString() + " TRỐNG DỮ LIỆU");
            }

            if(s.equals("ERROR02"))
            {
                Toast.makeText(ActivityDangKy.this, "Dang ky that bai", Toast.LENGTH_LONG).show();
                tt.setText(s.toString() + " Tài khoản đã tồn tại");
            }
            if(s.equals("ERROR03"))
            {
                Toast.makeText(ActivityDangKy.this, "Dang ky that bai", Toast.LENGTH_LONG).show();
                tt.setText(s.toString() + " Lỗi server đăng ký");
            }
            if(s.equals("ERROR04"))
            {
                Toast.makeText(ActivityDangKy.this, "Dang ky that bai", Toast.LENGTH_LONG).show();
                tt.setText(s.toString() + " Lỗi tạo csdl");
            }
            super.onPostExecute(s);
        }
    }
}