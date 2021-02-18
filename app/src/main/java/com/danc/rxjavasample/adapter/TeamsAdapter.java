package com.danc.rxjavasample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.rxjavasample.databinding.ListPlayersBinding;
import com.danc.rxjavasample.databinding.ListTeamsBinding;
import com.danc.rxjavasample.model.Teams.Data;

import java.util.ArrayList;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private static final String TAG = "TeamsAdapter";
    List<Data> dataList = new ArrayList<>();

    public void setData(@Nullable List<Data> data){
        if (data == null){
            return;
        }
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListTeamsBinding binding = ListTeamsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TeamsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.bindData(data);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TeamsViewHolder extends RecyclerView.ViewHolder {

        ListTeamsBinding binding;

        public TeamsViewHolder(@NonNull ListTeamsBinding bindingView) {
            super(bindingView.getRoot());
            this.binding = bindingView;

        }

        public void bindData(Data teamsData) {
            binding.teamFullName.setText(teamsData.getFull_name());
            binding.nickName.setText(teamsData.getName());
            binding.teamLocation.setText(teamsData.getCity());
        }
    }
}
