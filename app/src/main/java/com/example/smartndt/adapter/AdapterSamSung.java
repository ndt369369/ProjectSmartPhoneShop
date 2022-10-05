package com.example.smartndt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartndt.R;
import com.example.smartndt.activity.IphoneActivity;
import com.example.smartndt.activity.SamSungActivity;
import com.example.smartndt.activity.ThongTinSPActivity;
import com.example.smartndt.modle.SanPham;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSamSung extends RecyclerView.Adapter<AdapterSamSung.ViewHolder> {
    Context context;
    List<SanPham> sanPhamList;

    public AdapterSamSung(SamSungActivity context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_samsungitem,parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tvTenSamSung.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSamSung.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" vnđ");
        Glide.with(context).load(sanPham.getHinhAnhSanPham()).into(holder.imageSamSungMain);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThongTinSPActivity.class);
                intent.putExtra("thongtinsanpham",sanPhamList.get(position));
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
        ImageView imageSamSungMain;
        TextView tvTenSamSung,tvGiaSamSung;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSamSungMain = itemView.findViewById(R.id.img_samsung_main);
            tvTenSamSung = itemView.findViewById(R.id.tv_ten_samsung_main);
            tvGiaSamSung = itemView.findViewById(R.id.tv_gia_samsung_main);
        }
    }
}
