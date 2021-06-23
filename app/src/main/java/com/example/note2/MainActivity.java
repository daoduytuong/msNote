package com.example.note2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.note2.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerlayout;
    NavigationView navigationview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        actionToolBar();
    } private void anhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        drawerlayout = (DrawerLayout) findViewById (R.id.drawerLayout);
        navigationview = (NavigationView) findViewById(R.id.navigationView);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        // Loại bỏ tiểu đề mặc định
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Mặc định nếu bạn muốn hiện thị biểu tượng có hình mũi tên như là nút bấm quay trở lại cửa sổ trước
        toolbar.setNavigationIcon(R.drawable.ic_account);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
            //Để mở Drawer , khi người dùng chạm vào button
        });
    }

//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch ((item.getItemId()))
//        {
//            case R.id.mSetting:
//            {
//                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_LONG).show();
//                break;
//            }
//
//            case R.id.mLogout:
//            {
//                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case R.id.mSupport:
//            {
//                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
}