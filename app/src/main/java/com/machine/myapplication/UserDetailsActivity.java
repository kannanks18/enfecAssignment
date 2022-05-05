package com.machine.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.machine.myapplication.databinding.ActivityUserDetailsBinding;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {
    ActivityUserDetailsBinding binding;
    MainViewmodel viewmodel;
    RecycleUsersAdapter adapter;
    String name;
    String userId;
    ArrayList<PostData> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewmodel = new ViewModelProvider(this).get(MainViewmodel.class);

        name = getIntent().getStringExtra("name");
        userId = getIntent().getStringExtra("userId");
        setupListView(binding.recyclerView);
        viewmodel.getPosts();
        viewmodel.getmAction().observe(this, new Observer<ActivityAction>() {
            @Override
            public void onChanged(ActivityAction action) {
                switch (action.getmAction()) {

                    case ActivityAction.GETPOSTSSUCCESS:
                        for (PostData postData : action.getPostData()) {
                            if (postData.getUserId()==Integer.parseInt(userId)) {
                                arrayList.add(postData);
                            }
                        }
                        adapter.setDataList(arrayList);
                        adapter.notifyDataSetChanged();
                        break;

                    case ActivityAction.API_ERROR:
                        Toast.makeText(UserDetailsActivity.this, action.getError(), Toast.LENGTH_LONG).show();
                        break;


                }
            }
        });

    }


    private void setupListView(RecyclerView recyclerView) {
        adapter = new RecycleUsersAdapter(name);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}