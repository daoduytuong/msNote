package com.example.note2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        return convertView;
    }
}
