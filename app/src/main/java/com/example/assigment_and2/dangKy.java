package com.example.assigment_and2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_and2.database.Dbhelper;

public class dangKy extends AppCompatActivity {

    EditText edt_user, edt_password, edt_Repass;
    Button btn_dangky;

    Dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edt_user = findViewById(R.id.edt_dangky_user);
        edt_password = findViewById(R.id.edt_dangky_pass);
        edt_Repass = findViewById(R.id.edt_dangky_Repass);
        btn_dangky = findViewById(R.id.btn_dangky);
        dbhelper = new Dbhelper(this);
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_password.getText().toString();
                String Repass = edt_Repass.getText().toString();
                if (user.equals("") || pass.equals("") || Repass.equals("")) {
                    Toast.makeText(dangKy.this, "can nhap du lieu", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(Repass)) {
                        boolean checkUser = dbhelper.checkUser(user);
                        if (checkUser == false) {
                            boolean insert = dbhelper.InsertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(dangKy.this, "dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(dangKy.this, dangNhap.class));
                                finish();
                            } else {
                                Toast.makeText(dangKy.this, "That bai !", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(dangKy.this, "TAI KHOAN DA TON TAI !!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(dangKy.this, "mat khau nhap LAI sai !!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}