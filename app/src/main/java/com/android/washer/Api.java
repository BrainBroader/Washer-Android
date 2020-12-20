package com.android.washer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String CONFIG = "https://kkoliou.github.io/Washer-Configuration/";

    @GET("washers.json")
    Call<List<WasherModel>> getWashers();
}
