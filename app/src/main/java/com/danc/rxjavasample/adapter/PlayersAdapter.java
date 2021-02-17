package com.danc.rxjavasample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.danc.rxjavasample.databinding.ListPlayersBinding;
import com.danc.rxjavasample.model.Players.Data;
import com.danc.rxjavasample.model.Players.PlayersModel;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {

    Context context;
    List<Data> playersModelList;

    public PlayersAdapter(Context context, List<Data> playersModels){
        this.context = context;
        this.playersModelList = playersModels;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPlayersBinding binding = ListPlayersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlayersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        Data data = playersModelList.get(position);
        holder.bind(data);
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

        public void bind(Data data){
            binding.fName.setText(data.getFirst_name());
            binding.lName.setText(data.getLast_name());
            binding.tName.setText(String.format("Team: %s", data.getTeam().getName()));
        }
    }
}
