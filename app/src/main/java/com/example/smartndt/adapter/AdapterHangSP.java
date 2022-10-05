package com.example.smartndt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartndt.activity.HuaweiActivity;
import com.example.smartndt.activity.OppoActivity;
import com.example.smartndt.activity.SamSungActivity;
import com.example.smartndt.activity.XiaomiActivity;
import com.example.smartndt.modle.HangSP;
import com.example.smartndt.activity.IphoneActivity;
import com.example.smartndt.activity.MainActivity;
import com.example.smartndt.R;

import java.util.List;

public class AdapterHangSP extends RecyclerView.Adapter<AdapterHangSP.ViewHolder> {
    Context context;
    List<HangSP> hangSPList;

    public AdapterHangSP(MainActivity context, List<HangSP> hangSPList) {
        this.context = context;
        this.hangSPList = hangSPList;
    }

    public AdapterHangSP(Context context, AdapterHangSP adapterHangSP) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_hangsp,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
       HangSP hangSP = hangSPList.get(position);
        Glide.with(context).load(hangSP.getLoGoHang()).into(holder.imgHang);
        holder.imgHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(context, IphoneActivity.class);
                        intent.putExtra("idhangsanpham",hangSPList.get(position).getId());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context,SamSungActivity.class);
                        intent1.putExtra("idhangsanpham",hangSPList.get(position).getId());
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, HuaweiActivity.class);
                        intent2.putExtra("idhangsanpham",hangSPList.get(position).getId());
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, XiaomiActivity.class);
                        intent3.putExtra("idhangsanpham",hangSPList.get(position).getId());
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(context, OppoActivity.class);
                        intent4.putExtra("idhangsanpham",hangSPList.get(position).getId());
                        view.getContext().startActivity(intent4);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hangSPList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHang;
        public ViewHolder(View itemView) {
            super(itemView);
            imgHang = itemView.findViewById(R.id.img_hang);
        }
    }
}
