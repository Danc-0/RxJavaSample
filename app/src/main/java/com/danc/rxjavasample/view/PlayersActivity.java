package com.danc.rxjavasample.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danc.rxjavasample.adapter.PlayersAdapter;
import com.danc.rxjavasample.databinding.ActivityPlayersBinding;
import com.danc.rxjavasample.model.Players.DataX;
import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.network.RetrofitClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayersActivity extends AppCompatActivity {

    private static final String TAG = PlayersActivity.class.getSimpleName();
    public PlayersAdapter adapter = new PlayersAdapter();
    private Subscription subscription;
    ActivityPlayersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvPlayers.setLayoutManager(manager);
        binding.rvPlayers.setAdapter(adapter);
        getPlayers();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    public void getPlayers(){
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
                        Log.d(TAG, "In onError(): " + e.getMessage());
                    }

                    @Override
                    public void onNext(PlayersModel playersModels) {
                        List<DataX> dataX = playersModels.getData();
                        adapter.setGitHubRepos(dataX);

                    }
                });
    }
}