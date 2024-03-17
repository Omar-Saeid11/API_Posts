package com.example.apisession.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apisession.databinding.ActivityCommentsBinding;
import com.example.apisession.model.Comments;
import com.example.apisession.network.RetrofitReconnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {
    ActivityCommentsBinding binding;
    private AdapterCommentItem adapterCommentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the adapter with an empty list
        adapterCommentItem = new AdapterCommentItem(new ArrayList<>());

        Intent intent = getIntent();
        String postId = intent.getStringExtra("postId");
        if (postId != null) { // Explicit null check
            RetrofitReconnection.getInstance().getComments(Integer.parseInt(postId)).enqueue(new Callback<List<Comments>>() {

                @Override
                public void onResponse(@NonNull Call<List<Comments>> call, @NonNull Response<List<Comments>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Check for successful response
                        adapterCommentItem.setList(response.body());
                        binding.recyclerComments.setAdapter(adapterCommentItem);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Comments>> call, @NonNull Throwable t) {
                    // Handle the error scenario, e.g., log the error or show a message to the user
                    Log.e("CommentsActivity", "Error fetching comments", t);
                }
            });
        } else {
            // Handle the scenario where postId is null, e.g., show an error message or finish the activity
            Log.e("CommentsActivity", "Post ID is null");
            finish();
        }
    }
}
