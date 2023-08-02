package com.example.assigment_and2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_and2.database.Dbhelper;

public class dangNhap extends AppCompatActivity {

    EditText edt_user, edt_pass;
    TextView btn_register, btn_forgotPassword;
    Button btn_dangNhap;

    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edt_user = findViewById(R.id.edt_dangnhap_user);
        edt_pass = findViewById(R.id.edt_dangnhap_pass);
        btn_register = findViewById(R.id.btn_register);
        btn_dangNhap = findViewById(R.id.btn_dang_nhap);
        btn_forgotPassword = findViewById(R.id.btn_forgotPassword);
        dbhelper = new Dbhelper(this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangNhap.this, dangKy.class));
                finish();
            }
        });
        btn_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();

                if (user.equals("")||pass.equals("")){
                    Toast.makeText(dangNhap.this, "can nhap du lieu !!", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkUserPass = dbhelper.checkUserPass(user, pass);
                    if (checkUserPass == true){
                        Toast.makeText(dangNhap.this, "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dangNhap.this, manHinhChinh.class));
                        finish();
                    }else{
                        Toast.makeText(dangNhap.this, "that bai !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}