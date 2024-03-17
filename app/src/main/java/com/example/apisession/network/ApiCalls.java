package com.example.apisession.network;

import com.example.apisession.model.Comments;
import com.example.apisession.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("comments")
    Call<List<Comments>> getComments(@Query("postId") int postId);
}

