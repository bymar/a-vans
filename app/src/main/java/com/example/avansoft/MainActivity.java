package com.example.avansoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.avansoft.apiclient.ApiClient;
import com.example.avansoft.apiclient.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dataToBeDisplayed;
    private AdapterConfig adapterConfig;
    private List<ApiData> apiDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiDataList = new ArrayList<>();
        dataToBeDisplayed = findViewById(R.id.recyclerview);
        dataToBeDisplayed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapterConfig = new AdapterConfig(MainActivity.this, apiDataList);
        dataToBeDisplayed.setAdapter(adapterConfig);
        populateResponseDTO();
    }

    public void populateResponseDTO() {
        ApiClient.getClient().create(ApiInterface.class).getApiDataList().enqueue(new Callback<ApiDataResponseDTO>() {
            @Override
            public void onResponse(Call<ApiDataResponseDTO> call, Response<ApiDataResponseDTO> response) {
                if (response.code() == 200 && response.body() != null) {
                    apiDataList.addAll(response.body().getApiDataList());
                    adapterConfig.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ApiDataResponseDTO> call, Throwable t) {

            }
        });
    }

}