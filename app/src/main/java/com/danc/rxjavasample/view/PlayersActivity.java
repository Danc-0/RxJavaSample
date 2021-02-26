package com.danc.rxjavasample.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Predicate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.rxjavasample.R;
import com.danc.rxjavasample.adapter.PaginationAdapter;
import com.danc.rxjavasample.adapter.PlayersAdapter;
import com.danc.rxjavasample.adapter.TeamsAdapter;
import com.danc.rxjavasample.databinding.ActivityMainScreenBinding;
import com.danc.rxjavasample.databinding.ActivityPlayersBinding;
import com.danc.rxjavasample.databinding.ActivityTeamsBinding;
import com.danc.rxjavasample.listeners.PaginationScrollListener;
import com.danc.rxjavasample.model.Movie;
import com.danc.rxjavasample.model.Players.Data;
import com.danc.rxjavasample.model.Players.PlayersModel;
import com.danc.rxjavasample.network.RetrofitClient;
import com.danc.rxjavasample.viewmodel.PlayersViewModel;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayersActivity extends AppCompatActivity {

    private static final String TAG = PlayersActivity.class.getSimpleName();
    public PlayersAdapter adapter = new PlayersAdapter();
    private Subscription subscription;
    ActivityPlayersBinding binding;
    private PlayersViewModel playersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvPlayers.setLayoutManager(manager);
        binding.rvPlayers.setAdapter(adapter);

        playersViewModel = new ViewModelProvider(this).get(PlayersViewModel.class);
        getPlayers();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    public void getPlayers() {
        playersViewModel.getPlayersListObserver().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                if (data != null){
                    adapter.setPlayersData(data);
                }
            }
        });

        playersViewModel.getPlayers();
    }
}