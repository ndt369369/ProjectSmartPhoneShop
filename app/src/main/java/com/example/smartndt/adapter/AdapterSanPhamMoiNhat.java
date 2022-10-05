package com.example.smartndt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartndt.R;
import com.example.smartndt.modle.SanPham;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSanPhamMoiNhat extends RecyclerView.Adapter<AdapterSanPhamMoiNhat.ViewHolder> {
    Context context ;
     List<SanPham> listSPMN;

    public AdapterSanPhamMoiNhat(Context context, List<SanPham> listSPMN) {
        this.context = context;
        this.listSPMN = listSPMN;
    }

    @NonNull
    @Override
    public AdapterSanPhamMoiNhat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spnm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSanPhamMoiNhat.ViewHolder holder, int position) {
        SanPham sanPham = listSPMN.get(position);
        holder.tvTenSPMN.setText(sanPham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSPMN.setText("Giá: "+decimalFormat.format(sanPham.getGiaSanPham())+" vnđ");
        Glide.with(context).load(sanPham.getHinhAnhSanPham()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listSPMN.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTenSPMN, tvGiaSPMN;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_sanphammoinhat);
            tvTenSPMN = itemView.findViewById(R.id.tv_tenspmoinhat);
            tvGiaSPMN = itemView.findViewById(R.id.tv_giaspmoinhat);
        }
    }
}
