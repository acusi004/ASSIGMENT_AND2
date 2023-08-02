package com.example.assigment_and2.Adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_and2.R;

public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tv_tenSp, tv_giaSp, tv_soLuongSp, btn_sua, btn_xoa;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_tenSp = itemView.findViewById(R.id.tv_tenSP);
        tv_giaSp = itemView.findViewById(R.id.tv_giaSp);
        tv_soLuongSp = itemView.findViewById(R.id.tv_soluongSp);
        btn_sua  =itemView.findViewById(R.id.btn_sua);
        btn_xoa  =itemView.findViewById(R.id.btn_xoa);
    }
}
