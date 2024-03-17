package com.example.apisession.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apisession.databinding.ItemPostBinding;
import com.example.apisession.model.Comments;

import java.util.List;

public class AdapterCommentItem extends RecyclerView.Adapter<AdapterCommentItem.Holder> {
    private List<Comments> list;

    // Constructor to initialize the list
    public AdapterCommentItem(List<Comments> list) {
        this.list = list;
    }

    // Setter method with notification to the adapter
    public void setList(List<Comments> list) {
        this.list = list;
        notifyDataSetChanged(); // Notify the adapter of the data set change
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

    public static class Holder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public Holder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Comments comments) {
            binding.postBody.setText(comments.getBody());
            // Corrected method to set top padding
            binding.postBody.setPadding(binding.postBody.getPaddingLeft(), 1, binding.postBody.getPaddingRight(), binding.postBody.getPaddingBottom());
        }
    }
}
