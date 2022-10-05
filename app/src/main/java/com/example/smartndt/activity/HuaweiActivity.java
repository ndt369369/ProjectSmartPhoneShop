package com.example.smartndt.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartndt.R;
import com.example.smartndt.adapter.AdapterHuawei;
import com.example.smartndt.modle.SanPham;
import com.example.smartndt.ulti.LinkServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuaweiActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerView;
private AdapterHuawei adapterHuawei;
private List<SanPham> sanPhamList;
private int idhsp = 0;
private int page = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huawei);
        anhXa();
        getSanPhamHuawei(page);
        getIdHang();
        toolbarHuawei();
    }

    private void getIdHang() {
        idhsp = getIntent().getIntExtra("idhangsanpham", -1);
        Log.d("huawei", String.valueOf(idhsp));
    }

    private void getSanPhamHuawei(int Page) {
        String link = LinkServer.sanphamtheoidhang + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             if (response != null){
                 try {
                     JSONArray jsonArray = new JSONArray(response);
                     for (int i = 0; i < response.length(); i++){
                         JSONObject jsonObject = jsonArray.getJSONObject(i);
                         int id = jsonObject.getInt("id");
                         String tenSP = jsonObject.getString("tensp");
                         int giaSP = jsonObject.getInt("giasp");
                         String hinhSP = jsonObject.getString("hinhanhsp");
                         String moTaSP = jsonObject.getString("motasp");
                         int idhang = jsonObject.getInt("idhangsp");
                         sanPhamList.add(new SanPham(id,tenSP,giaSP,hinhSP,moTaSP,idhang));
                         adapterHuawei.notifyDataSetChanged();
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idhangsanpham", String.valueOf(idhsp));
                return hashMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void toolbarHuawei() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolba_huawei);
        recyclerView = findViewById(R.id.rv_sp_huawei);
        sanPhamList  = new ArrayList<>();
        adapterHuawei =  new AdapterHuawei(this,sanPhamList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHuawei);
    }
}