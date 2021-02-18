package com.danc.rxjavasample.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danc.rxjavasample.adapter.PlayersAdapter;
import com.danc.rxjavasample.adapter.TeamsAdapter;
import com.danc.rxjavasample.databinding.ActivityPlayersBinding;
import com.danc.rxjavasample.databinding.ActivityTeamsBinding;
import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.model.Teams.Data;
import com.danc.rxjavasample.model.Teams.TeamsModel;
import com.danc.rxjavasample.network.RetrofitClient;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamsActivity extends AppCompatActivity {

    private static final String TAG = TeamsActivity.class.getSimpleName();
    public TeamsAdapter adapter = new TeamsAdapter();;
    private Subscription subscription;
    ActivityTeamsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvTeams.setLayoutManager(manager);
        binding.rvTeams.setItemAnimator(new DefaultItemAnimator());
        binding.rvTeams.setAdapter(adapter);
        getAllTeams();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    public void getAllTeams(){
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

                    }

                    @Override
                    public void onNext(TeamsModel teamsModel) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<Data> dataList = teamsModel.getData();
                                adapter.setData(dataList);
                            }
                        });


                    }
                });
    }


}