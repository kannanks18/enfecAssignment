package com.machine.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.machine.myapplication.databinding.ItemUsersBinding;

import java.util.Collections;
import java.util.List;


public class RecycleUsersAdapter extends RecyclerView.Adapter<RecycleUsersAdapter.ViewHolder> {


    private List<PostData> datumList;
    String name;
    public RecycleUsersAdapter(String name) {
        this.name=name;
        this.datumList = Collections.emptyList();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUsersBinding binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.title.setText(datumList.get(position).getTitle());
        holder.binding.userName.setText(name);
        holder.binding.body.setText(datumList.get(position).getBody());
    }


    @Override
    public int getItemCount() {
        return datumList.size();
    }
    public void setDataList(List<PostData> data) {
        this.datumList = data;
        notifyDataSetChanged();
    }
    public void emptyList() {
        this.datumList = Collections.emptyList();
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemUsersBinding binding;

        public ViewHolder(@NonNull ItemUsersBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }


    }
}
