package com.danc.rxjavasample.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.danc.rxjavasample.viewmodel.PlayersViewModel;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeamsActivity extends AppCompatActivity {

    private static final String TAG = TeamsActivity.class.getSimpleName();
    public TeamsAdapter adapter = new TeamsAdapter();
    private Subscription subscription;
    ActivityTeamsBinding binding;

    private PlayersViewModel playersViewModel;

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

        playersViewModel = new ViewModelProvider(this).get(PlayersViewModel.class);

        getAllTeams();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getAllTeams() {
        playersViewModel.getTeamsListObserver().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                if (data != null){
                    adapter.setData(data);
                }
            }
        });

        playersViewModel.getAllTeams();
    }

}