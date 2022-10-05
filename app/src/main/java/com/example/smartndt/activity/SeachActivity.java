package com.example.smartndt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartndt.R;
import com.example.smartndt.adapter.SeachAdapter;
import com.example.smartndt.modle.SanPham;
import com.example.smartndt.ulti.LinkServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SeachActivity extends AppCompatActivity {
SearchView searchView;
List<SanPham> sanPhamList;
RecyclerView recyclerView;
SeachAdapter seachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        anhxa();
    }

    private void anhxa() {
        sanPhamList = new ArrayList<>();
        getSanPham();
        seachAdapter = new SeachAdapter(this,sanPhamList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView = findViewById(R.id.rv_seachsp);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(seachAdapter);
        searchView = findViewById(R.id.search_sanpham);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<SanPham> filterModList = filter(sanPhamList, newText);
                seachAdapter.setfilter(filterModList);
                return true;
            }
        });
        TextView tvCancel = findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeachActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<SanPham> filter(List<SanPham> sanPhamList, String query) {
        query = query.toLowerCase();
        final List<SanPham> filterModeList = new ArrayList<>();
        for (SanPham modal : sanPhamList) {
            final String text = modal.getTenSanPham().toLowerCase();
            if (text.startsWith(query)) {
                filterModeList.add(modal);
            }
        }
        return filterModeList;
    }

    private void getSanPham() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LinkServer.sanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                 if (response != null){
                     for (int i = 0 ; i< response.length(); i++){

                         try {
                             JSONObject jsonObject = response.getJSONObject(i);
                             int id = jsonObject.getInt("id");
                             String tenSP = jsonObject.getString("tensp");
                             int giaSP = jsonObject.getInt("giasp");
                             String hinhSP = jsonObject.getString("hinhanhsp");
                             String moTaSP = jsonObject.getString("motasp");
                             int idhang = jsonObject.getInt("idhang");
                             sanPhamList.add(new SanPham(id,tenSP,giaSP,hinhSP,moTaSP,idhang));
                             seachAdapter.notifyDataSetChanged();
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
}