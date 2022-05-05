package com.machine.myapplication;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MainViewmodel extends AndroidViewModel {

    APIService apiService;
    Dialog loading;
    MainRepository repository;

    public MainViewmodel(@NonNull Application application) {
        super(application);
        mAction = new MutableLiveData<>();
        repository = MainRepository.getInstance();

    }

    MutableLiveData<ActivityAction> mAction;

    public MutableLiveData<ActivityAction> getmAction() {
        mAction = repository.getmAction();
        return mAction;
    }

    public void setmAction(MutableLiveData<ActivityAction> mAction) {
        this.mAction = mAction;
    }


    public void getUsers() {
//        initLoading();
        apiService = NetworkService.getRetrofitClient("test").create(APIService.class);
        repository.getUsers(apiService);
    }

    public void getPosts() {
//        initLoading();
        apiService = NetworkService.getRetrofitClient("test").create(APIService.class);
        repository.getPosts(apiService);
    }
//
//    public void initLoading() {
//        binding.progressCircular.setVisibility(View.VISIBLE);
//        binding.main.setVisibility(View.GONE);
//    }
//
//    public void cancelLoading() {
//        binding.progressCircular.setVisibility(View.GONE);
//        binding.main.setVisibility(View.VISIBLE);
//    }
//    public void recyclerLoading() {
//        binding.recyclerviewProgressBar.setVisibility(View.VISIBLE);
//        binding.recyclerview.setVisibility(View.GONE);
//    }
//
//    public void cancelrecyclerLoading() {
//        binding.recyclerviewProgressBar.setVisibility(View.GONE);
//        binding.recyclerview.setVisibility(View.VISIBLE);
//    }
//
}
