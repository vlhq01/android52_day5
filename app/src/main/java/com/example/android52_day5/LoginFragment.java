package com.example.android52_day5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements IRegisterListener {

    private static final String TAG = "Fragment" + LoginFragment.class.getName();

    private String p1;
    private String p2;

    @BindView(R.id.txtSu)
    TextView tv_SignUp;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.btnlogin)
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }






    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            p1 = getArguments().getString("P1");
            p2 = getArguments().getString("P2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        tv_SignUp.setOnClickListener(v -> gotoSignUp());

        btnLogin.setOnClickListener(v -> gotoLogin());

    }

    private void gotoLogin() {
        Gson gson = new Gson();
        String data = gson.toJson(userInfo, UserInfo.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("LOGIN_STATE", true);
        editor.putString("USER_INFO", data);
        editor.apply();

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setUserInfo(userInfo);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).addToBackStack("LOGIN").commit();
    }

    private void gotoSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        signUpFragment.setRegisterListener(this);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, signUpFragment).addToBackStack("SIGNUP").commit();
    }



    private UserInfo userInfo;

    @Override
    public void onRegisterSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        edtPhoneNumber.setText(userInfo.getPhone());
    }

    @Override
    public void onRegisterCancel() {

    }
}
