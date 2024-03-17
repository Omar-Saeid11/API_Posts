package com.example.apisession.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apisession.model.Posts;
import com.example.apisession.databinding.ItemPostBinding;

import java.util.List;

public class AdapterPostItem extends RecyclerView.Adapter<AdapterPostItem.Holder> {
    private List<Posts> list;
    private OnItemClick onItemClick;

    // Constructor to initialize the list
    public AdapterPostItem(List<Posts> list) {
        this.list = list;
    }

    // Setter method with notification to the adapter
    public void setList(List<Posts> list) {
        this.list = list;
        notifyDataSetChanged(); // Notify the adapter of the data set change
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0; // Check for null to avoid NullPointerException
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public Holder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                if (onItemClick != null && getLayoutPosition() != RecyclerView.NO_POSITION) {
                    onItemClick.onClick(list.get(getLayoutPosition()));
                }
            });
        }

        public void bind(Posts posts) {
            binding.postTitle.setText(posts.getTitle());
            binding.postBody.setText(posts.getBody());
        }
    }

    interface OnItemClick {
        void onClick(Posts post); // Renamed parameter for clarity
    }
}
