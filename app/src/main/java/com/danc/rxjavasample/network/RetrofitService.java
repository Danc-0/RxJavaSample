package com.danc.rxjavasample.network;

import com.danc.rxjavasample.model.Players.DataX;
import com.danc.rxjavasample.model.Players.PlayersModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitService {

    @GET("api/v1/players")
    Observable<PlayersModel> getAllPlayers();

}
