package com.example.smartndt.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.smartndt.modle.GioHang;
import com.example.smartndt.modle.HangSP;
import com.example.smartndt.ulti.LinkServer;
import com.example.smartndt.R;
import com.example.smartndt.modle.SanPham;
import com.example.smartndt.adapter.AdapterHangSP;
import com.example.smartndt.adapter.AdapterSanPham;
import com.example.smartndt.adapter.AdapterSanPhamMoiNhat;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper viewFlipper;
    TextView textView;
RecyclerView recyclerView,recyclerView1,recyclerView2;
List<HangSP> hangSPList;
List<SanPham> sanPhamList;
AdapterSanPham adapterSanPham;
AdapterHangSP adapterHangSP;
int id = 0;
String tenhangsanpham = "";
String hinhanhhang = "";
public static ArrayList<GioHang> gioHangArrayList;
    private DrawerLayout drawerMain;
    private NavigationView navMain;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        AcViewParger();

    }
    private void anhxa() {
        hangSPList = new ArrayList<>();
        getDulieuHangsp();
        adapterHangSP = new AdapterHangSP(this,hangSPList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        recyclerView = findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHangSP);
        recyclerView1 = findViewById(R.id.rv_listspm);
        sanpham();
        viewFlipper = findViewById(R.id.vfip_main);
        drawerMain = findViewById(R.id.draw_main);
        navMain = findViewById(R.id.nav_main);
        navMain = findViewById(R.id.nav_main);
        navMain.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu_main);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerMain.openDrawer(GravityCompat.START);
            }
        });
        textView = findViewById(R.id.tv_seachmain);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SeachActivity.class);
                startActivity(intent);
            }
        });
        if (gioHangArrayList != null){

        }else {
            gioHangArrayList = new ArrayList<>();
        }

    }
    private void sanpham() {
        sanPhamList = new ArrayList<>();
        getsanpham();
        adapterSanPham = new AdapterSanPham(this,sanPhamList);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this,2);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapterSanPham);
    }
    private void getsanpham() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LinkServer.sanphammoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0 ; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("idm");
                            String tenSP = jsonObject.getString("tenspm");
                            int giaSP = jsonObject.getInt("giaspm");
                            String hinhSP = jsonObject.getString("hinhanhspm");
                            String moTaSP = jsonObject.getString("motaspm");
                            int idhang = jsonObject.getInt("idhangm");
                            sanPhamList.add(new SanPham(id,tenSP,giaSP,hinhSP,moTaSP,idhang));
                            adapterSanPham.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void getDulieuHangsp() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LinkServer.hangsanpham,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response  != null){
                            for (int i = 0 ; i< response.length(); i ++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    id = jsonObject.getInt("id");

                                    tenhangsanpham = jsonObject.getString("tenhangsanpham");
                                    hinhanhhang = jsonObject.getString("hinhanhhang");
                                    hangSPList.add(new HangSP(id,tenhangsanpham,hinhanhhang));
                                    adapterHangSP.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            Log.e("fptt","loi");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

    }
    private void AcViewParger() {
        ArrayList<String> mangQC = new ArrayList<>();
        mangQC.add("https://danviet.mediacdn.vn/upload/1-2020/images/2020-02-08/Quang-cao-iPhone-11-chup-anh-ban-dem-dep-me-ly-lu-mo-tat-ca-akrales_190914_3628_0060-1581146457-width660height440.jpg");
        mangQC.add("https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/5/4/1041230/Cam-Bien-AI-PORTRAIT.JPG");
        mangQC.add("https://cdn.tgdd.vn/Files/2020/02/10/1235488/samsung-galaxy-z-flip_1280x720-800-resize.jpg");
        mangQC.add("https://nangluongxanh.info.vn/upload/80124/20201201/grab654d72146557.jpg");
        for (int i = 0 ; i < mangQC.size() ; i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(this).load(mangQC.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setBackground(getResources().getDrawable(R.drawable.boder_vfp));


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart :
                Intent intent = new Intent(MainActivity.this,GioHangActivity.class);
                startActivity(intent);
                break;
        }
        if (item.getItemId() == android.R.id.home) {
            if (drawerMain.isDrawerOpen(GravityCompat.START)) {
                drawerMain.closeDrawer(GravityCompat.START);
            }
            drawerMain.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_natification,menu);
        getMenuInflater().inflate(R.menu.menu_shopping,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_trangchu:
                break;
            case R.id.menu_sanpham:
                break;
            case R.id.menu_call:
                String phone = "0964839150";
                break;
            case R.id.menu_facebook:
                String url = "https://www.facebook.com/profile.php?id=100009996818983";
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
                break;
            case R.id.menu_thongtinlienhe:

                break;
            case R.id.menu_dangxuat:
                break;
        }
        drawerMain.closeDrawer(GravityCompat.START);
        return false;
    }
}