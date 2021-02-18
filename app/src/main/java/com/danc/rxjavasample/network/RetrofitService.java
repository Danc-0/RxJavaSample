package com.danc.rxjavasample.network;

import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.model.Teams.TeamsModel;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitService {

    @GET("api/v1/players")
    Observable<PlayersModel> getAllPlayers();

    @GET("api/v1/teams")
    Observable<TeamsModel> getAllTeams();

}
