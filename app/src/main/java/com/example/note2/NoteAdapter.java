package com.example.note2;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter {
    Context context;
    int Layout;
    List<Note> arrNote;

    public NoteAdapter(Context context, int layout, ArrayList<Note> arrNote) {
        this.context = context;
        Layout = layout;
        this.arrNote = arrNote;
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
        return 0;
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+arrNote.get(position).getID(), Toast.LENGTH_LONG).show();
                XacNhanXoa(arrNote.get(position).getID());
            }
        });


        return convertView;
    }

    private void XacNhanXoa(final int id)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Xac nhan xoa");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //new ActivityNote.XoaNote(id).execute("http://tuongdhqn-001-site1.ftempurl.com/DelNote.php");
                Toast.makeText(context, "     fsfsfsfgsfs fsf s f", Toast.LENGTH_LONG).show();
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


}
