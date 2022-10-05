package com.example.smartndt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartndt.R;
import com.example.smartndt.modle.GioHang;
import com.example.smartndt.modle.SanPham;

import java.text.DecimalFormat;

public class ThongTinSPActivity extends AppCompatActivity {
    Button btnBuyToNow;
    ImageView imageView;
    TextView tvTenSP,tvGiaSP,tvMoTa;
    Toolbar toolbar;
    int id = 0;
    String tensp = "";
    int giasp = 0;
    String hinhanhsp ="";
    String motasp = "";
    int idhang = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_spactivity);
        anhXa();
        toolbarTTSP();
        getInformation();
        buyToNowClick();
    }

    private void buyToNowClick() {
        btnBuyToNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = 1;
               boolean exists = false;
                if (MainActivity.gioHangArrayList.size() > 0){
                     for (int i = 0 ; i < MainActivity.gioHangArrayList.size(); i++){
                         if (MainActivity.gioHangArrayList.get(i).getIdsp() == id){
                             MainActivity.gioHangArrayList.get(i).setSoLuong(MainActivity.gioHangArrayList.get(i).getSoLuong()+ soluong);
                             MainActivity.gioHangArrayList.get(i).setGiaSP(giasp * MainActivity.gioHangArrayList.get(i).getSoLuong());
                         exists = true;
                         }
                     }
                     if (exists == false){
                         long giamoi = soluong * giasp;
                         MainActivity.gioHangArrayList.add(new GioHang(id,tensp,giamoi,hinhanhsp,soluong));
                     }
                }
                else {
                    long giamoi = soluong * giasp;
                    MainActivity.gioHangArrayList.add(new GioHang(id,tensp,giamoi,hinhanhsp,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getInformation() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
         id = sanPham.getId();
         tensp = sanPham.getTenSanPham();
         giasp = sanPham.getGiaSanPham();
         hinhanhsp = sanPham.getHinhAnhSanPham();
         motasp = sanPham.getMoTaSanPham();
         idhang = sanPham.getIdHangSanPham();
        tvTenSP.setText(tensp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaSP.setText("Giá: "+decimalFormat.format(giasp)+" vnđ");
        tvMoTa.setText(motasp );
        Glide.with(this).load(hinhanhsp).into(imageView);

    }

    private void toolbarTTSP() {
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
        btnBuyToNow = findViewById(R.id.btn_buytonow);
        imageView = findViewById(R.id.img_ttchitietsp);
        tvGiaSP = findViewById(R.id.tv_giaspchitiet);
        tvTenSP = findViewById(R.id.tv_tenspchitiet);
        toolbar = findViewById(R.id.toolba_ttsp);
        tvMoTa = findViewById(R.id.tv_motaspchitiet);
    }
}