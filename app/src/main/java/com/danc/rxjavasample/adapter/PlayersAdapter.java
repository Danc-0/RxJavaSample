package com.danc.rxjavasample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.rxjavasample.databinding.ListPlayersBinding;
import com.danc.rxjavasample.model.GitHubRepo;
import com.danc.rxjavasample.model.Players.DataX;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {

    private List<DataX> playersModelList = new ArrayList<>();

    public PlayersAdapter(){
    }

    public void setGitHubRepos(@Nullable List<DataX> repos) {
        if (repos == null) {
            return;
        }
        playersModelList.clear();
        playersModelList.addAll(repos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPlayersBinding binding = ListPlayersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlayersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        DataX dataX = playersModelList.get(position);
        holder.bind(dataX);
    }

    @Override
    public int getItemCount() {
        return playersModelList.size();
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        ListPlayersBinding binding;

        public PlayersViewHolder(@NonNull ListPlayersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DataX dataX){
            binding.fName.setText(dataX.getFirst_name());
            binding.lName.setText(dataX.getLast_name());
            binding.tName.setText(String.format("Team: %s", dataX.getTeam().getFull_name()));
        }
    }
}
