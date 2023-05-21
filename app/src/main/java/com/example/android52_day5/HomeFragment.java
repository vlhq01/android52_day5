package com.example.android52_day5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.tvWelcome)
    TextView tvWelcome;

    private UserInfo userInfo;

    public HomeFragment() {
        // Required empty public constructor
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        String data = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE).getString("USER_INFO", null);
        if (data != null) {
            Gson gson = new Gson();
            UserInfo userInfo = gson.fromJson(data, UserInfo.class);
            if (userInfo != null) {
                tvWelcome.setText(userInfo.toString());
            }
        }
    }
}
