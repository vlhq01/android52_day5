package com.example.android52_day5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {

    @BindView(R.id.txtbacktologin)
    TextView tvLogin;
    @BindView(R.id.editTextTextPersonName)
    EditText edtName;
    @BindView(R.id.editTextTextPersonEmail)
    EditText edtEmail;
    @BindView(R.id.editTextTextPersonPassword)
    EditText edtPassword;
    @BindView(R.id.editTextTextPersonphone)
    EditText edtPhoneNumber;
    @BindView(R.id.btnsignup)
    Button btnJoinUs;

    private IRegisterListener registerListener;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(edtName.getText().toString());
        userInfo.setPasswords(edtPassword.getText().toString());
        userInfo.setEmail(edtEmail.getText().toString());
        userInfo.setPhone(edtPhoneNumber.getText().toString());

        if (registerListener != null) {
            registerListener.onRegisterSuccess(userInfo);
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }

    private void backToLogin() {
        if (registerListener != null){
            registerListener.onRegisterCancel();
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void setRegisterListener(IRegisterListener registerListener) {
        this.registerListener = registerListener;
    }
}
