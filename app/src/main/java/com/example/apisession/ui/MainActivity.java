package com.example.apisession.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apisession.databinding.ActivityMainBinding;
import com.example.apisession.model.Posts;
import com.example.apisession.network.RetrofitReconnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AdapterPostItem adapterPostItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the adapter with an empty list
        adapterPostItem = new AdapterPostItem(new ArrayList<>());

        RetrofitReconnection.getInstance().getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(@NonNull Call<List<Posts>> call, @NonNull Response<List<Posts>> response) {
                if (response.isSuccessful() && response.body() != null) { // Check for successful response and non-null body
                    adapterPostItem.setList(response.body());
                    binding.recyclerPosts.setAdapter(adapterPostItem);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Posts>> call, @NonNull Throwable t) {
                // Handle the error scenario, e.g., log the error or show a message to the user
                Log.e("MainActivity", "Error fetching posts", t);
            }
        });

        adapterPostItem.setOnItemClick(post -> {
            Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
            intent.putExtra("postId", String.valueOf(post.getId()));
            startActivity(intent);
        });
    }
}
