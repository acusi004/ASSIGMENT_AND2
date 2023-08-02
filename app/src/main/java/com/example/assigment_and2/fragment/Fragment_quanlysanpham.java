package com.example.assigment_and2.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_and2.Adapter.sanPhamAdapter;
import com.example.assigment_and2.DAO.SanPhamDAO;
import com.example.assigment_and2.R;
import com.example.assigment_and2.model.sanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_quanlysanpham extends Fragment {

    AlertDialog alertDialog;
    SanPhamDAO sanPhamDAO;
    RecyclerView recyclerView;
    ArrayList<sanPham> list = new ArrayList<>();

    sanPhamAdapter adapter;

    FloatingActionButton btn_them;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)        {
        View view   = inflater.inflate(R.layout.fragment_quanlysanpham, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        btn_them = view.findViewById(R.id.btn_add);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sanPhamDAO = new SanPhamDAO(getContext());
        list = sanPhamDAO.getALl();

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new sanPhamAdapter(sanPhamDAO, getContext(), list);
        recyclerView.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog, null);
                builder.setView(view);
                AlertDialog alertDialog1 = builder.create();
                EditText edt_tenSp, edt_GiaSp, edt_soLuongSp;
                Button btn_them;

                edt_tenSp = view.findViewById(R.id.edt_tensp);
                edt_GiaSp = view.findViewById(R.id.edt_giasp);
                edt_soLuongSp = view.findViewById(R.id.edt_soluongsp);
                btn_them = view.findViewById(R.id.btn_themsp);

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tensp = edt_tenSp.getText().toString();
                        String giasp = edt_GiaSp.getText().toString();
                        String soluongsp = edt_soLuongSp.getText().toString();

                        if (tensp.equals("")||giasp.equals("")||soluongsp.equals("")){
                            Toast.makeText(getContext(), "Cần nhập dữ liệu", Toast.LENGTH_SHORT).show();
                        }else{
                            sanPham sp = new sanPham();
                            sp.setMaSp(sp.getMaSp());
                            sp.setGiaBan(Integer.parseInt(edt_GiaSp.getText().toString()));
                            sp.setSoLuong(Integer.parseInt(edt_soLuongSp.getText().toString()));
                            sp.setTenSp(edt_tenSp.getText().toString());

                            sanPhamDAO.add(sp);
                            list = sanPhamDAO.getALl();
                            adapter = new sanPhamAdapter(sanPhamDAO, getContext(), list);
                            recyclerView.setAdapter(adapter);
                            alertDialog1.dismiss();

                        }

                    }
                });

                builder.show();

            }

        });


    }
    @Override
    public void onPause() {
        super.onPause();
    }


}