package com.danc.rxjavasample.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danc.rxjavasample.model.Players.Data;

import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.model.Teams.TeamsModel;
import com.danc.rxjavasample.network.RetrofitClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayersViewModel extends ViewModel {

    private static final String TAG = "playersViewModel";
    private Subscription subscription;

    public PlayersViewModel() {
        listAllPlayers = new MutableLiveData<>();
        listAllTeams = new MutableLiveData<>();
    }

    private MutableLiveData<List<Data>> listAllPlayers;
    private MutableLiveData<List<com.danc.rxjavasample.model.Teams.Data>> listAllTeams;

    public LiveData<List<Data>> getPlayersListObserver() {
        return listAllPlayers;
    }

    public LiveData<List<com.danc.rxjavasample.model.Teams.Data>> getTeamsListObserver() {
        return listAllTeams;
    }

    public void getPlayers() {
        subscription = RetrofitClient.getInstance()
                .getAllPlayers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayersModel>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Load is Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listAllPlayers.postValue(null);
                    }

                    @Override
                    public void onNext(PlayersModel playersModels) {
                        listAllPlayers.postValue(playersModels.getData());

                    }
                });
    }

    public void getAllTeams() {
        subscription = RetrofitClient.getInstance()
                .getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamsModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listAllTeams.postValue(null);
                    }

                    @Override
                    public void onNext(TeamsModel teamsModel) {
                        listAllTeams.postValue(teamsModel.getData());

                    }
                });
    }
}
