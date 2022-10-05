package com.example.smartndt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartndt.R;
import com.example.smartndt.adapter.AdapterGioHang;
import com.example.smartndt.modle.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
RecyclerView recyclerView;
 public  static  AdapterGioHang adapterGioHang;
Toolbar toolbar;
public  static  TextView tvThongB,tvTongTien;
Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
        toolbarGH();
        CheckTB();
        EvenUltis();
    }

    public static void EvenUltis() {
        long tongtien = 0;
        for (int i=0; i < MainActivity.gioHangArrayList.size(); i ++){
            tongtien = tongtien + MainActivity.gioHangArrayList.get(i).getGiaSP();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTien.setText(decimalFormat.format(tongtien)+"vnd");
    }

    private void CheckTB() {
        if (MainActivity.gioHangArrayList.size()<=0){
            adapterGioHang.notifyDataSetChanged();
            tvThongB.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }else {
            adapterGioHang.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            tvThongB.setVisibility(View.INVISIBLE);
        }
    }

    private void toolbarGH() {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
    }

    private void anhXa() {
      recyclerView = findViewById(R.id.rv_giohang);
        toolbar = findViewById(R.id.toolba_giohang);
        tvThongB = findViewById(R.id.tv_thongbao);
        btnThanhToan = findViewById(R.id.btn_thanhtoangiohang);
        tvTongTien = findViewById(R.id.tv_tongtien);
        adapterGioHang = new AdapterGioHang(this,MainActivity.gioHangArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
    }
}