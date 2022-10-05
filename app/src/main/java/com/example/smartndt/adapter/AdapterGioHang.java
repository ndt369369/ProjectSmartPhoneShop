package com.example.smartndt.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartndt.R;
import com.example.smartndt.activity.GioHangActivity;
import com.example.smartndt.activity.MainActivity;
import com.example.smartndt.activity.ThongTinSPActivity;
import com.example.smartndt.modle.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {
    Context context;
    List<GioHang> gioHangList;

    public AdapterGioHang(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.tvTenSPGH.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaSPGH.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " vnđ");
        Glide.with(context).load(gioHang.getHinHanhSP()).into(holder.imgGH);
        holder.btnValuse.setText(String.valueOf(gioHang.getSoLuong()));
        int soluong = Integer.parseInt(holder.btnValuse.getText().toString());
        if (soluong >= 10) {
            holder.btnPlus.setVisibility(View.INVISIBLE);
            holder.btnMinnum.setVisibility(View.VISIBLE);
        } else if (soluong <= 1) {
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinnum.setVisibility(View.INVISIBLE);
        } else if (soluong > 1) {
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinnum.setVisibility(View.VISIBLE);
        }

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongMoi = Integer.parseInt(holder.btnValuse.getText().toString()) + 1;
                int sLHT = MainActivity.gioHangArrayList.get(position).getSoLuong();
                long giaHT = MainActivity.gioHangArrayList.get(position).getGiaSP();
                MainActivity.gioHangArrayList.get(position).setSoLuong(soLuongMoi);
                long giaTienMoi = (giaHT * soLuongMoi) / sLHT;
                MainActivity.gioHangArrayList.get(position).setGiaSP(giaTienMoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiaSPGH.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " vnđ");
                GioHangActivity.EvenUltis();
                if (soLuongMoi > 8) {
                    holder.btnPlus.setVisibility(View.INVISIBLE);
                    holder.btnMinnum.setVisibility(View.VISIBLE);
                    holder.btnValuse.setText(String.valueOf(soLuongMoi));
                } else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinnum.setVisibility(View.VISIBLE);
                    holder.btnValuse.setText(String.valueOf(soLuongMoi));
                }
            }
        });
        holder.btnMinnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongMoi = Integer.parseInt(holder.btnValuse.getText().toString()) - 1;
                int sLHT = MainActivity.gioHangArrayList.get(position).getSoLuong();
                long giaHT = MainActivity.gioHangArrayList.get(position).getGiaSP();
                MainActivity.gioHangArrayList.get(position).setSoLuong(soLuongMoi);
                long giaTienMoi = (giaHT * soLuongMoi) / sLHT;
                MainActivity.gioHangArrayList.get(position).setGiaSP(giaTienMoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiaSPGH.setText("Giá: " + decimalFormat.format(gioHang.getGiaSP()) + " vnđ");
                GioHangActivity.EvenUltis();
                if (soLuongMoi < 2) {
                    holder.btnMinnum.setVisibility(View.INVISIBLE);
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnValuse.setText(String.valueOf(soLuongMoi));
                } else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinnum.setVisibility(View.VISIBLE);
                    holder.btnValuse.setText(String.valueOf(soLuongMoi));
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Delete the product");
                alertDialog.setMessage("Do you want to delete the product?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.gioHangArrayList.size() <= 0) {
                            GioHangActivity.tvThongB.setVisibility(View.VISIBLE);
                            GioHangActivity.adapterGioHang.notifyDataSetChanged();
                        } else {
                            GioHangActivity.tvThongB.setVisibility(View.INVISIBLE);
                            MainActivity.gioHangArrayList.remove(position);
                            GioHangActivity.adapterGioHang.notifyDataSetChanged();
                            GioHangActivity.EvenUltis();
                        }
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GioHangActivity.adapterGioHang.notifyDataSetChanged();
                        GioHangActivity.EvenUltis();
                    }
                });
                alertDialog.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGH;
        Button btnMinnum, btnValuse, btnPlus;
        TextView tvTenSPGH, tvGiaSPGH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGH = itemView.findViewById(R.id.img_giohangitem);
            btnMinnum = itemView.findViewById(R.id.btn_minnus);
            btnValuse = itemView.findViewById(R.id.btn_values);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            tvTenSPGH = itemView.findViewById(R.id.tv_tengioihang);
            tvGiaSPGH = itemView.findViewById(R.id.tv_giatrimonhang);
        }
    }
}
