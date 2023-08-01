package com.example.avansoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.avansoft.config.AdapterConfig;
import com.example.avansoft.domain.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterConfig adapterConfig;
    private List<UserData> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        String API_URL = "https://run.mocky.io/v3/ce47ee53-6531-4821-a6f6-71a188eaaee0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
            response -> {

                try {
                    JSONArray jsonArray = response.getJSONArray("users");

                    for (int index = 0; index < jsonArray.length(); index++) {
                        JSONObject currentObject = jsonArray.getJSONObject(index);
                        UserData userData = new UserData();

                        userData.setId(currentObject.getString("id"));
                        userData.setName(currentObject.getString("name"));
                        userData.setAge(currentObject.getString("age"));
                        userData.setEmail(currentObject.getString("email"));
                        userDataList.add(userData);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                adapterConfig = new AdapterConfig(MainActivity.this, userDataList);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 10));
                recyclerView.setAdapter(adapterConfig);
            },

            error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

}