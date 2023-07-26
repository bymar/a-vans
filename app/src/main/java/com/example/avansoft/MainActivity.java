package com.example.avansoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.avansoft.config.AdapterConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private AdapterConfig adapterConfig;
    private List<ApiData> apiDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiDataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        String API_URL = "https://run.mocky.io/v3/ce47ee53-6531-4821-a6f6-71a188eaaee0";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("users");

                    for (int index = 0; index < jsonArray.length(); index++) {
                        JSONObject currentObject = jsonArray.getJSONObject(index);
                        ApiData apiData = new ApiData();

                        apiData.setId(currentObject.getString("id"));
                        apiData.setName(currentObject.getString("name"));
                        apiData.setAge(currentObject.getString("age"));
                        apiData.setEmail(currentObject.getString("email"));
                        apiDataList.add(apiData);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                adapterConfig = new AdapterConfig(MainActivity.this, apiDataList);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 10));
                recyclerView.setAdapter(adapterConfig);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

}