package com.example.avansoft.apiclient;

import com.example.avansoft.ApiDataResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v3/ce47ee53-6531-4821-a6f6-71a188eaaee0")
    Call<ApiDataResponseDTO> getApiDataList();
}
