package com.example.note2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityNoteDetail extends AppCompatActivity {
    TextInputEditText textViewTD, textViewND;
    FloatingActionButton button;
    int id;
    String td, nd;
    String Scolor;
    String Bcolor;
    Spinner color;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Anhxa();
        LoadSpiner();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get dữ liệu được gửi từ actiNote khi nhấn voà 1 note
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("ID");
        textViewTD.setText(bundle.getString("TD"));
        textViewND.setText(bundle.getString("ND"));
        Bcolor = bundle.getString("CL");

        //color.setSelection(getIndex(color, Bcolor))
       // color.setSelection(2);

        //button lưu
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                td = textViewTD.getText().toString().trim();
                nd = textViewND.getText().toString().trim();
                new PostToServer(id, td, nd, Scolor).execute("http://192.168.1.2/msNote/updateNote.php");
            }
        });
    }

    private void LoadSpiner() {
        ArrayList<String> arrColor = new ArrayList<>();
        arrColor.add("Mặc định");
        arrColor.add("Blue");
        arrColor.add("Green");
        arrColor.add("Orange");
        arrColor.add("Grey");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrColor);
        color.setAdapter(adapter);
        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (arrColor.get(position)) {
                    case "Mặc định":
                        Scolor = Bcolor; //nhận lại màu cũ để nếu k có thay đổi, set như cũ
                        break;
                    case "Blue":
                        Scolor = "1";
                        break;
                    case "Green":
                        Scolor = "2";
                        break;
                    case "Orange":
                        Scolor = "3";
                        break;
                    case "Grey":
                        Scolor = "4";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Anhxa() {
        textViewTD = findViewById(R.id.editTextTD);
        textViewND = findViewById(R.id.editTextND);
        button = findViewById(R.id.fbtnUpdate);
        toolbar = findViewById(R.id.toolbarNoteDetail);
        color = findViewById(R.id.spinner3);
    }

    //menu góc trên phải
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnCopy:
            {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("EditText", textViewND.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                clipData.getDescription();
                Toast.makeText(ActivityNoteDetail.this, "Đã lưu vào bộ nhớ tạm", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.mnDelete:
            {
                XacNhanXoa(id);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    class PostToServer extends AsyncTask<String, Void, String>
    {
        OkHttpClient client = new OkHttpClient();
        int id;
        String TD;
        String ND;
        String cl;

        public PostToServer(int id, String td, String nd, String cl) {
            this.id = id;
            this.TD = td;
            this.ND = nd;
            this.cl = cl;
        }
        @Override
        protected String doInBackground(String... strings)
        {
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("id", String.valueOf(id)) //các giá trị phải name phải trùng với $_POST trong php
                    .addFormDataPart("tieude", TD)
                    .addFormDataPart("noidung", ND)
                    .addFormDataPart("color", cl)
                    .setType(MultipartBody.FORM)
                    .build();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute(); //thực hiện request, thao tác với server
                return  response.body().string(); //return dữ liệu được server trả về
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)  //s là dữ liệu được return dòng 186
        {

           // Toast.makeText(ActivityADD.this, "nd "+s, Toast.LENGTH_SHORT).show();
            if(s.equals("true"))
            {
                Toast.makeText(ActivityNoteDetail.this, "update thanh cong", Toast.LENGTH_LONG).show();
                Intent intent =new Intent(ActivityNoteDetail.this, ActivityNote.class);
                startActivity(intent);
            }
            if(s.equals("ERROR07"))
            {
                Toast.makeText(ActivityNoteDetail.this, "Loi truyen du lieu", Toast.LENGTH_LONG).show();

            }
            if(s.equals("ERROR8"))
            {
                Toast.makeText(ActivityNoteDetail.this, "Update that bai", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
    private void XacNhanXoa(final int id)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Xác nhận xoá");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new XoaNote(id).execute("http://192.168.1.2/msNote/DelNote.php");
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    class  XoaNote extends AsyncTask<String, String, String>
    {
        OkHttpClient client = new OkHttpClient();
        int id;
        public XoaNote(int id) {
            this.id = id;
        }
        @Override
        protected String doInBackground(String... strings)
        {
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("id", String.valueOf(id))
                    .setType(MultipartBody.FORM)
                    .build();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("true"))
            {
                Toast.makeText(ActivityNoteDetail.this, "Xoa thanh cong", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivityNoteDetail.this, ActivityNote.class); //quay về acti show list Note
                startActivity(intent);
            }
            if(s.equals("ERROR09"))
            {
                Toast.makeText(ActivityNoteDetail.this, "Loi truyen du lieu", Toast.LENGTH_LONG).show();
            }
            if(s.equals("ERROR10"))
            {
                Toast.makeText(ActivityNoteDetail.this, "Xoa that bai", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
}