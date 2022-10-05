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
import com.example.smartndt.adapter.AdapterSamSung;
import com.example.smartndt.modle.SanPham;
import com.example.smartndt.ulti.LinkServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SamSungActivity extends AppCompatActivity {
int idsp = 0;
int page = 1;
AdapterSamSung adapterSamSung;
List<SanPham> listSanPhamSamSung;
private Toolbar toobarSamSung;
private RecyclerView recyclerSamSung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam_sung);
        anhXa();
        getDataSamSung(page);
        toobarSamSung();
        getIdSp();
    }

    private void getDataSamSung(int Page) {
        String link = LinkServer.sanphamtheoidhang + String.valueOf(Page);
        StringRequest stringRequest  = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0 ; i < response.length() ; i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String tenSP = jsonObject.getString("tensp");
                            int giaSP = jsonObject.getInt("giasp");
                            String hinhSP = jsonObject.getString("hinhanhsp");
                            String moTaSP = jsonObject.getString("motasp");
                            int idhang = jsonObject.getInt("idhangsp");
                            listSanPhamSamSung.add(new SanPham(id, tenSP, giaSP, hinhSP, moTaSP, idhang));
                            adapterSamSung.notifyDataSetChanged();
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
                HashMap hashmap = new HashMap();
                hashmap.put("idhangsanpham",String.valueOf(idsp));
                return hashmap;
            }
        }
                ;
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(stringRequest);
    }

    private void toobarSamSung() {
        setSupportActionBar(toobarSamSung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toobarSamSung.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toobarSamSung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
       toobarSamSung = findViewById(R.id.toolba_samsung);
       recyclerSamSung = findViewById(R.id.rv_sp_samsung);
       listSanPhamSamSung = new ArrayList<>();
       adapterSamSung = new AdapterSamSung(this,listSanPhamSamSung);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerSamSung.setLayoutManager(layoutManager);
        recyclerSamSung.setAdapter(adapterSamSung);
    }
    private void getIdSp() {
        idsp = getIntent().getIntExtra("idhangsanpham",-1);
        Log.d("idss", String.valueOf(idsp));
    }
}