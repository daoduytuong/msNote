package com.example.note2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class  ActivityAddImage extends AppCompatActivity {
    Button button;
    ImageView imageView;
    TextInputEditText editText;
    int CODE = 1;
    String a;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        button = findViewById(R.id.btnUpimg);
        imageView = findViewById(R.id.imgUP);
        editText = findViewById(R.id.editTextAddIMG);
        UpIMG();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpIMG().execute("http://192.168.1.2/msNote/addImage.php");
            }
        });
    }

    private void UpIMG()
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode == CODE && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            path = getRealPathFromURI(uri);
            Log.d("path", path);
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    class UpIMG extends AsyncTask<String, Void, String>
    {
        OkHttpClient client = new OkHttpClient();
        String TD = editText.getText().toString();
        @Override
        protected String doInBackground(String... strings)
        {
            File file = new File(path);
            String type = getType(file.getPath());
            String filepath = file.getAbsolutePath();
            Log.d("pathtype", type);
            Log.d("path2", filepath);

            RequestBody requestBodyfile = RequestBody.create(MediaType.parse(type), file);
            Log.d("path", "ok");
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("upIMG", filepath.substring(filepath.lastIndexOf("/") + 1), requestBodyfile)
                    .addFormDataPart("tieude", TD)
                    .setType(MultipartBody.FORM)
                    .build();
            Log.d("path", "ok2");
            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();
            Log.d("path", "ok3");
            try {
                Response response = client.newCall(request).execute();
                return  response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("path", "ok4");
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("true"))
            {
                Toast.makeText(ActivityAddImage.this, "Thêm thành công ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ActivityAddImage.this, ActivityNote.class);
                startActivity(intent);

            }
            if(s.equals("false"))
            {
                Toast.makeText(ActivityAddImage.this, "Loi ", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }

    private String getType(String path) {
        String ex = MimeTypeMap.getFileExtensionFromUrl(path);
        return  MimeTypeMap.getSingleton().getMimeTypeFromExtension(ex);
    }


}