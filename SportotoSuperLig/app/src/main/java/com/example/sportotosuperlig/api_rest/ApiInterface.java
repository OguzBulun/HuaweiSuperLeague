package com.example.sportotosuperlig.api_rest;

import com.example.sportotosuperlig.models.Player;
import com.example.sportotosuperlig.models.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("1ca7f50e-1729-4576-bb33-ec9c44975150")
    public Call<List<Team>> teamList();

    @GET("72b7e237-b6a4-497c-838f-e19a76ac9116")
    public Call<List<Player>> playerList();
}
