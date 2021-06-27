package com.example.note2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityADD extends AppCompatActivity {

    EditText textViewTD;
    EditText textViewND;
    String td, nd;
    String Scoler;
    Spinner spinner;
    String USER;
    Toolbar toolbar;
    //TextView ADD;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        textViewTD = findViewById(R.id.editTextAddTD);
        textViewND = findViewById(R.id.editTextAddND);
        button = findViewById(R.id.fbtnADD);
        spinner = findViewById(R.id.spinner2);
        toolbar=findViewById(R.id.toolbarDki);

        //set toolbar
        setSupportActionBar(toolbar);

        //get User đc truyền từ trang trước
        Intent intent = getIntent();
        USER = intent.getStringExtra(ActivityNote.USERNAME);


        ArrayList<String> arrColor = new ArrayList<>();
        arrColor.add("Mặc định");
        arrColor.add("Blue");
        arrColor.add("Green");
        arrColor.add("Orange");
        arrColor.add("Grey");

        //set adapter cho spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrColor);
        spinner.setAdapter(adapter);

        // sự kiện spiner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (arrColor.get(position))
                {
                    case "Mặc định":
                        Scoler="NULL";
                        break;
                    case "Blue":
                        Scoler="1";
                        break;
                    case "Green":
                        Scoler = "2";
                        break;
                    case   "Orange":
                        Scoler="3";
                        break;
                    case  "Grey":
                        Scoler ="4";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //bắt sự kiện button thêm
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                td = textViewTD.getText().toString().trim();
                nd = textViewND.getText().toString().trim();
                //ADD.setText(USER + td  + nd ); //kiem tra du lieu
               // new AddToSV(USER,td,nd).execute("http://tuongdhqn-001-site1.ftempurl.com/addNote.php");
                new PostToServer(USER,td,nd,Scoler).execute("http://tuongdhqn-001-site1.ftempurl.com/addnote.php");
            }
        });
    }
    //gửi dữ liệu lên server
    class PostToServer extends AsyncTask<String, Void, String>
    {
        OkHttpClient client = new OkHttpClient();
        String user;
        String TD;
        String ND;
        String cl;

        public PostToServer(String user, String td, String nd, String cl) { //phương thức khởi tạo
            this.user = user;
            this.TD = td;
            this.ND = nd;
            this.cl = cl;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            //đoạn này chưa hiểu rõ
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("username", user) //set dữ liệu vào biến POST để thg php lất $_POST[username]
                    .addFormDataPart("tieude", TD)
                    .addFormDataPart("noidung", ND)
                    .addFormDataPart("color", cl)
                    .setType(MultipartBody.FORM)
                    .build();

            Request request = new Request.Builder()
                    .url(strings[0]) //strings là URL
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return  response.body().string(); //lấy dữ liệu trả về dòng 153
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) { // 145 truyền cho thg này
            if(s.equals("true"))
            {
                Toast.makeText(ActivityADD.this, "Thêm thành công ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivityADD.this, ActivityNote.class);
                startActivity(intent); //cho nó về màn hình chính

            }
            if(s.equals("ERROR05"))
            {
                Toast.makeText(ActivityADD.this, "Loi truyen du lieu", Toast.LENGTH_LONG).show();

            }
            if(s.equals("ERROR6"))
            {
                Toast.makeText(ActivityADD.this, "Add that bai", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
//
//
//    class AddToSV extends AsyncTask<String, Void, String>
//    {
//        OkHttpClient client = new OkHttpClient();
//        String User;
//        String Tieude;
//        String NoiDung;
//
//        public AddToSV(String user, String tieude, String noiDung) {
//            User = user;
//            Tieude = tieude;
//            NoiDung = noiDung;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            RequestBody requestBody = new MultipartBody.Builder()
//                    .addFormDataPart("username", "Admin")
//                    .addFormDataPart("tieude", "Tieude")
//                    .addFormDataPart("noidung", "NoiDung")
//                    .build();
//
//            Request request = new Request.Builder()
//                    .url(strings[0])
//                    .post(requestBody)
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                return response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//            super.onPostExecute(s);
//            Toast.makeText(ActivityADD.this, s+"", Toast.LENGTH_LONG).show();
////            if(s.equals("true"))
////            {
////                Toast.makeText(ActivityADD.this, s+"Them thanh cong", Toast.LENGTH_LONG).show();
////            }else if (s.equals("ERROR6"))
////            {
////                Toast.makeText(ActivityADD.this, s+"Them that bai", Toast.LENGTH_LONG).show();
////            }else if(s.equals("ERROR5"))
////            {
////                Toast.makeText(ActivityADD.this, s+"Loi truyen du lieu", Toast.LENGTH_LONG).show();
////            }
//        }
//    }
}