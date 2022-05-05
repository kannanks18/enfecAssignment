package com.machine.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.machine.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<UsersData> arrayList = new ArrayList<>();
    MainViewmodel viewmodel;
    String name = "";
    String userId = "";

    @Override
    protected void onResume() {
        super.onResume();
        viewmodel.getUsers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewmodel = new ViewModelProvider(this).get(MainViewmodel.class);

        binding.btnSubmit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.data.getText())) {
            Toast.makeText(this,"Enter Valid Id",Toast.LENGTH_LONG).show();
            } else if (!validate()) {
                Toast.makeText(this,"Enter Valid Id",Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, UserDetailsActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("userId", binding.data.getText().toString());
                startActivity(intent);

            }
        });
        viewmodel.getmAction().

                observe(this, new Observer<ActivityAction>() {
                    @Override
                    public void onChanged(ActivityAction action) {
                        switch (action.getmAction()) {
                            case ActivityAction.GETUSERSUCCESS:
                                for (UsersData usersData : action.getUsersDataLists()) {
                                    arrayList.add(usersData);

                                }
                                break;

                            case ActivityAction.API_ERROR:
                                Toast.makeText(MainActivity.this, action.getError(), Toast.LENGTH_LONG).show();
                                break;


                        }
                    }
                });

    }

    private boolean validate() {
        boolean valid=false;
        for (int i=0; i<arrayList.size();i++)
        {
            if (binding.data.getText().toString().equals(arrayList.get(i).getId().toString())){
                valid =true;
                name=arrayList.get(i).getName();
            }
        }
        return valid;
    }
}