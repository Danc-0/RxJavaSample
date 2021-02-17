package com.danc.rxjavasample.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danc.rxjavasample.R;
import com.danc.rxjavasample.adapter.GitHubRepoAdapter;
import com.danc.rxjavasample.adapter.PlayersAdapter;
import com.danc.rxjavasample.databinding.ActivityPlayersBinding;
import com.danc.rxjavasample.model.GitHubRepo;
import com.danc.rxjavasample.model.Players.Data;
import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.network.GitHubClient;
import com.danc.rxjavasample.network.RetrofitClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayersActivity extends AppCompatActivity {

    private static final String TAG = PlayersActivity.class.getSimpleName();
    public PlayersAdapter adapter;
    private Subscription subscription;
    ActivityPlayersBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayersBinding.inflate(LayoutInflater.from(context));
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recylerview.setLayoutManager(manager);

        getPlayers();
    }

    public void getPlayers(){
        subscription = RetrofitClient.getInstance()
                .retrofitService.getAllPlayers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Data>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Load is Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Data> playersModels) {
                        adapter = new PlayersAdapter(context, playersModels);
                        binding.recylerview.setAdapter(adapter);
                    }
                });
    }
}