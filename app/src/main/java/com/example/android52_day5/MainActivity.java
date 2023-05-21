package com.example.android52_day5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    LoginFragment loginFragment;
    private void initView() {
        ButterKnife.bind(this);



        boolean isLogin = getSharedPreferences("LOGIN", MODE_PRIVATE).getBoolean("LOGIN_STATE", false);
        if (isLogin) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).addToBackStack("ADD").commit();
        } else {
            loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, loginFragment).addToBackStack("ADD").commit();
        }
    }
}