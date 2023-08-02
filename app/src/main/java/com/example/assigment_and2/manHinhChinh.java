package com.example.assigment_and2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.assigment_and2.Adapter.sanPhamAdapter;
import com.example.assigment_and2.fragment.Fragment_gioithieu;
import com.example.assigment_and2.fragment.Fragment_quanlysanpham;
import com.example.assigment_and2.model.sanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class manHinhChinh extends AppCompatActivity  {

    private DrawerLayout daDrawerLayout;
    private FloatingActionButton btn_them;
    Toolbar toolbar;

    RecyclerView recyclerView;

    Fragment fragment;
    sanPhamAdapter adapter;

    Context context= this;

    ArrayList<sanPham> list = new ArrayList<>();


    NavigationView navigationView;

    public void mimMap(){
        daDrawerLayout = findViewById(R.id.drawewrLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        mimMap();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, daDrawerLayout, toolbar,R.string.open, R.string.close);
        daDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        // bat su kien Menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.dangXuat){
                    AlertDialog.Builder builder = new AlertDialog.Builder(manHinhChinh.this);
                    builder.setTitle("Thông báo");
                    builder.setIcon(R.drawable.baseline_warning_amber_24);
                    builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
                    builder.setCancelable(false);


                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(manHinhChinh.this, dangNhap.class));
                            finish();
                        }
                    });

                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create();
                    builder.show();
                }else if(item.getItemId()== R.id.GioiThieu){
                    toolbar.setTitle("Gioi thieu");
                    fragment = new Fragment_gioithieu();
                }else if (item.getItemId()== R.id.QuanLySanPham) {
                    toolbar.setTitle("Quan ly san pham");
                    fragment = new Fragment_quanlysanpham();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

                daDrawerLayout.close();
                return true;
            }
        });
        //----------------





    }

}