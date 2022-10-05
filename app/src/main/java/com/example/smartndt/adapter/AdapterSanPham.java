package com.example.smartndt.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartndt.activity.MainActivity;
import com.example.smartndt.R;
import com.example.smartndt.activity.ThongTinSPActivity;
import com.example.smartndt.modle.SanPham;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder> {
    MainActivity context;
    List<SanPham> sanPhamList;

    public AdapterSanPham(MainActivity context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tvTenSP.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSP.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" vnđ");
        Glide.with(context).load(sanPham.getHinhAnhSanPham()).into(holder.imgAnhSP);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThongTinSPActivity.class);
                intent.putExtra("thongtinsanpham", sanPhamList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhSP;
        TextView tvTenSP,tvGiaSP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhSP = itemView.findViewById(R.id.img_sanphammoi);
            tvGiaSP = itemView.findViewById(R.id.tv_giaspmoi);
            tvTenSP = itemView.findViewById(R.id.tv_tenspmoi);
        }
    }
}
