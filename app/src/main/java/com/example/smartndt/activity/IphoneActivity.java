package com.example.smartndt.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartndt.R;
import com.example.smartndt.adapter.AdapterIphone;
import com.example.smartndt.modle.SanPham;
import com.example.smartndt.ulti.LinkServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IphoneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<SanPham> sanPhamList;
    AdapterIphone adapterIphone;
    Toolbar toolbar;

    int idhdt = 0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphone);
        anhXa();
        toolbarIphone();
        getDataId();
        getDataIphone(page);
    }

    private void toolbarIphone() {
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

    private void getDataIphone(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext()); // dua request len cho server
        String link =  LinkServer.sanphamtheoidhang + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              if (response != null){
                  try {
                      JSONArray jsonArray = new JSONArray(response);
                      for (int i =0 ; i < jsonArray.length(); i++){
                          JSONObject jsonObject = jsonArray.getJSONObject(i);
                          int id = jsonObject.getInt("id");
                          String tenSP = jsonObject.getString("tensp");
                          int giaSP = jsonObject.getInt("giasp");
                          String hinhSP = jsonObject.getString("hinhanhsp");
                          String moTaSP = jsonObject.getString("motasp");
                          int idhang = jsonObject.getInt("idhangsp");
                          sanPhamList.add(new SanPham(id,tenSP,giaSP,hinhSP,moTaSP,idhang));
                          adapterIphone.notifyDataSetChanged();
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
                HashMap<String,String> hashMap = new HashMap<String,String>() ;
                hashMap.put("idhangsanpham",String.valueOf(idhdt));
                return hashMap;
            }
        };  requestQueue.add(stringRequest);  // doc du het du lieu ve
    }

    private void anhXa() {
        recyclerView = findViewById(R.id.rv_spiphone);
        sanPhamList = new ArrayList<>();
        adapterIphone = new AdapterIphone(this,sanPhamList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterIphone);
        toolbar = findViewById(R.id.toolba_iphone);
    }

    private void getDataId() {
        idhdt = getIntent().getIntExtra("idhangsanpham", -1);
        Log.d("idhangsanpham",idhdt+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);

        }
        return true;
    }
}