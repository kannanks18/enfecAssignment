package com.machine.myapplication;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIService {


    @GET("posts")
    Single<List<PostData>> getPosts();
    @GET("users")
    Single<List<UsersData>> getUsers();

}
