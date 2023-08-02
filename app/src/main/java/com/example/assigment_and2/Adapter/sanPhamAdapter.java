package com.example.assigment_and2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_and2.DAO.SanPhamDAO;
import com.example.assigment_and2.R;
import com.example.assigment_and2.manHinhChinh;
import com.example.assigment_and2.model.sanPham;

import java.util.ArrayList;

public class sanPhamAdapter extends RecyclerView.Adapter<MyViewHolder> {


    public SanPhamDAO sanPhamDAO;

    private final Context context;

    private ArrayList<sanPham> list;

    EditText edt_tensp, edt_soluongsp, edt_giasp;

    TextView tv_title;

    Button btn_themsp;

    public sanPhamAdapter(SanPhamDAO sanPhamDAO, Context context, ArrayList<sanPham> list) {
        this.sanPhamDAO = sanPhamDAO;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((manHinhChinh) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_rcv_sanpham, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_tenSp.setText(list.get(position).getTenSp());
        holder.tv_soLuongSp.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.tv_giaSp.setText(String.valueOf(list.get(position).getGiaBan()));


        // chuc nang xoa
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo !");

                builder.setMessage("Bạn có chắc chắn muốn xóa công việc này không?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int maSp = list.get(holder.getAdapterPosition()).getMaSp();
                        boolean check = sanPhamDAO.delete(maSp);
                        if (check) {
                            Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list = sanPhamDAO.getALl();
                            notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

               AlertDialog alertDialog =  builder.create();
                builder.show();
            }
        });

        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham sanP = list.get(holder.getAdapterPosition());
                ALerUpdate(sanP);
            }
        });


    }

    public void ALerUpdate(sanPham sanpham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        edt_tensp = view.findViewById(R.id.edt_tensp);
        edt_soluongsp = view.findViewById(R.id.edt_soluongsp);
        edt_giasp = view.findViewById(R.id.edt_giasp);

        tv_title = view.findViewById(R.id.tv_title);
        btn_themsp = view.findViewById(R.id.btn_themsp);

        btn_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giasp = Integer.parseInt(edt_giasp.getText().toString());
                int soluongsp = Integer.parseInt(edt_soluongsp.getText().toString());
                String tensp = edt_tensp.getText().toString();

                sanPham sp = new sanPham(sanpham.getMaSp(), giasp, soluongsp, tensp);
                boolean check = sanPhamDAO.update(sp);
                if (check) {
                    list.clear();
                    list = sanPhamDAO.getALl();
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                    Toast.makeText(context, "successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.show();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
