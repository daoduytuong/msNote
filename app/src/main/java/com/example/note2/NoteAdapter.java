package com.example.note2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NoteAdapter extends BaseAdapter{
    Context context;
    int Layout;
    List<Note> arrNote; //arr chính
    ArrayList<Note> ListNotetmp; //arr tạm  để lưu trữ khi tìm kiếm
    int posisionXoa;

    public NoteAdapter(Context context, int layout, ArrayList<Note> arrNote) {
        this.context = context;
        Layout = layout;
        this.arrNote = arrNote;
        this.ListNotetmp = new ArrayList<Note>();
        this.ListNotetmp.addAll(arrNote);
    }

    @Override
    public int getCount() {
        return arrNote.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(Layout,null);

        TextView txtTieuDe = convertView.findViewById(R.id.textViewTD);
        txtTieuDe.setText(arrNote.get(position).TieuDe);

        TextView txtND = convertView.findViewById(R.id.textViewND);
        txtND.setText(arrNote.get(position).NoiDung);

        ImageView imageView = convertView.findViewById(R.id.imageViewNote);
        //sự kiện khi click vào hình
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, ""+arrNote.get(position).getID(), Toast.LENGTH_LONG).show();
                XacNhanXoa(arrNote.get(position).getID());
                posisionXoa = position; //lấy posision ra để xuống dưới xoá
            }
        });
        return convertView;
    }
    //dùng để tìm kiếm trong trong mảng đã lấy về
    public void fillter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault()); //chuyển chuỗi về dạng chữ thường
        arrNote.clear();
        if(charText.length() == 0)
        {
            arrNote.addAll(ListNotetmp); // nếu không có nội dung tìm kiếm, thì hiển thị lại toàn bộ arr
        } else
        {
            for (Note not : ListNotetmp)
            {
                if(not.TieuDe.toString().toLowerCase(Locale.getDefault()) //lọc theo tiêu đề
                        .contains(charText))
                {
                    arrNote.add(not);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void fillterNhan(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        arrNote.clear();
        if(charText.length() == 0)
        {
            arrNote.addAll(ListNotetmp);
        } else
        {
            for (Note not : ListNotetmp)
            {
                if(not.Nhan.toString().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    arrNote.add(not);
                }
            }
        }
        notifyDataSetChanged();
    }
    private void XacNhanXoa(final int id)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Xoá ghi chú");

        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  new ActivityNote.XoaNote(id).execute("http://tuongdhqn-001-site1.ftempurl.com/DelNote.php");
                new  XoaNote(id).execute("http://192.168.1.2/msNote/DelNote.php");
             //   Toast.makeText(context, " Xoa.", Toast.LENGTH_LONG).show();
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
                ActivityNote.arrNote.remove(posisionXoa); // khi xoá ở server thành công thì xoá phần tử khỏi mảng
                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_LONG).show();
                notifyDataSetChanged(); //baó cáo, để reload listview
            }
            if(s.equals("ERROR09"))
            {
                Toast.makeText(context, "Loi truyen du lieu", Toast.LENGTH_LONG).show();
            }
            if(s.equals("ERROR10"))
            {
                Toast.makeText(context, "Xoa that bai", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
