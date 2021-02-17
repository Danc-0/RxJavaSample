package com.danc.rxjavasample.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.danc.rxjavasample.R;
import com.danc.rxjavasample.adapter.GitHubRepoAdapter;
import com.danc.rxjavasample.databinding.ActivityMainScreenBinding;
import com.danc.rxjavasample.model.GitHubRepo;
import com.danc.rxjavasample.network.GitHubClient;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainScreen extends AppCompatActivity {

    ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(MainActivity.class);
            }
        });

        binding.buttonBallDont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo(PlayersActivity.class);
            }
        });

    }

    public void goTo(Class toGoClass){
        Intent intent = new Intent(this, toGoClass);
        startActivity(intent);
    }
}