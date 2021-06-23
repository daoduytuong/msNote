package com.example.note2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityNote extends AppCompatActivity {
    ListView lv;
    Button button;
    Intent intent;
    Toolbar toolbar;
   FloatingActionButton buttonADD;
    ArrayList<Note> arrNote;
    String stringTD;
    String stringND;
    String USER;
    public static final String USERNAME = "USERNAME";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        lv = findViewById(R.id.listNote);
       buttonADD = findViewById(R.id.btnThem);
       toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("HisLog", MODE_PRIVATE);

        intent = getIntent();
        USER = intent.getStringExtra(ActiLogin.USERNAME);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadLV(USER).execute("http://tuongdhqn-001-site1.ftempurl.com/JSONnote.php");
            }
        });


        buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityNote.this, ActivityADD.class);
                intent.putExtra(USERNAME, USER);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDetail = new Intent(ActivityNote.this, ActivityNoteDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", arrNote.get(position).ID);
                bundle.putString("TD", arrNote.get(position).TieuDe);
                bundle.putString("ND", arrNote.get(position).NoiDung);
                bundle.putString("CL", arrNote.get(position).Nhan);
                intentDetail.putExtras(bundle);
                startActivity(intentDetail);

            }

        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XacNhanXoa(arrNote.get(position).ID);
                return false;
            }
        });

    }
    private void XacNhanXoa(final int id)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Xac nhan xoa");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new XoaNote(id).execute("http://tuongdhqn-001-site1.ftempurl.com/DelNote.php");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId()))
        {
            case R.id.mnSet:
            {
                Intent intent = new Intent(ActivityNote.this, ActivitySetting.class);
                startActivity(intent);
                break;
            }

            case R.id.mnLogout:
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(ActivityNote.this, ActiLogin.class);
                startActivity(intent);
                break;
            }


        }
        return super.onOptionsItemSelected(item);
    }

    class  LoadLV extends AsyncTask<String, String, String>
    {
        OkHttpClient client = new OkHttpClient();
        String user;
        public LoadLV(String user) {
            this.user = user;
        }
        @Override
        protected String doInBackground(String... strings)
        {
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("username", user)
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
            //textView.setText(s);
            arrNote = new ArrayList<Note>();
            try{
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0 ; i < jsonArray.length(); i++)
                {
                    JSONObject note = jsonArray.getJSONObject(i);
                    Note n = new Note(){
                    };
                    int id = note.getInt("id");
                    String us = note.getString("username");
                    String TieuDe = note.getString("TieuDe");
                    String NoiDung = note.getString("NoiDung");
                    String nhan = note.getString("Nhan");

                    stringTD = TieuDe;
                    stringND = NoiDung;

                    n.ID = id;
                    n.username=us;
                    n.TieuDe=TieuDe;
                    n.NoiDung=NoiDung;
                    n.Nhan=nhan;
                    arrNote.add(n);
                }
//                ArrayAdapter adapter = new ArrayAdapter(
//                        ActivityNote.this, android.R.layout.simple_list_item_1,arrNote
//                );
                NoteAdapter noteAdapter = new NoteAdapter(
                        ActivityNote.this,
                        R.layout.row_note,
                        arrNote
                        )
                {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        switch (arrNote.get(position).Nhan)
                        {
                            case "1":
                                view.setBackgroundColor(getResources().getColor(
                                        android.R.color.holo_blue_light
                                ));
                                break;
                            case "2":
                                view.setBackgroundColor(getResources().getColor(
                                        android.R.color.holo_green_dark
                                ));
                                break;
                            case "3":
                                view.setBackgroundColor(getResources().getColor(
                                        android.R.color.holo_orange_light
                                ));
                                break;
                            case "4":
                                view.setBackgroundColor(getResources().getColor(
                                        android.R.color.darker_gray
                                ));
                                break;
                        }
                        return view;
                    }
                };
                lv.setAdapter(noteAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
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
                Toast.makeText(ActivityNote.this, "Xoa thanh cong", Toast.LENGTH_LONG).show();
                new LoadLV(USER).execute("http://tuongdhqn-001-site1.ftempurl.com/JSONnote.php");
            }
            if(s.equals("ERROR09"))
            {
                Toast.makeText(ActivityNote.this, "Loi truyen du lieu", Toast.LENGTH_LONG).show();
            }
            if(s.equals("ERROR10"))
            {
                Toast.makeText(ActivityNote.this, "Xoa that bai", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
}